package vn.edu.hcmut.web.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.hcmut.core.form.RegisterForm;
import vn.edu.hcmut.core.form.UsersForm;
import vn.edu.hcmut.core.model.Mail;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.model.hibernate.CauHinhChung;
import vn.edu.hcmut.core.model.hibernate.Profiles;
import vn.edu.hcmut.core.model.hibernate.ProfilesRaw;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Courses;
import vn.edu.hcmut.core.model.hibernate.security.Role;
import vn.edu.hcmut.core.model.hibernate.security.User;
import vn.edu.hcmut.core.repository.hibernate.CauHinhChungImpl;
import vn.edu.hcmut.core.repository.hibernate.IProfiles;
import vn.edu.hcmut.core.repository.hibernate.IProfilesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategoriesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICourses;
import vn.edu.hcmut.core.repository.hibernate.security.IRole;
import vn.edu.hcmut.core.repository.hibernate.security.IUser;
import vn.edu.hcmut.core.repository.hibernate.security.IUsersRaw;
import vn.edu.hcmut.core.utilities.TextUtils;
import vn.edu.hcmut.core.utilities.encrypt.EncryptAESProvider;
import vn.edu.hcmut.core.validator.RegisterValidator;
import vn.edu.hcmut.web.model.CategoryModel;
import vn.edu.hcmut.web.model.CourseDetailModel;
import vn.edu.hcmut.web.model.CourseModel;

@Controller
public class MainController {
    @Autowired
    private RegisterValidator registerValidator;
    @Autowired
    private IProfiles profilesHibernateDAO;
    @Autowired
    private IProfilesRaw profilesRawHibernateDAO;
    @Autowired
    private vn.edu.hcmut.core.repository.IProfiles profilesDAO;
    @Autowired
    private IRole roleHibernateDAO;
    @Autowired
    private IUser userHibernateDAO;
    @Autowired
    private IUsersRaw usersRawHibernateDAO;
    @Autowired
    ICategories categoriesDAO;
    @Autowired
    ICategoriesRaw categoriesRawDAO;
    @Autowired
    ICourses coursesDAO;
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    public CauHinhChungImpl cauHinhChungDAO;
    @Autowired
    EncryptAESProvider encryptAESProvider;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == RegisterForm.class) {
            dataBinder.setValidator(registerValidator);
        }
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String viewHome(Model model) {
        try {
            List<Categories> categoriesList = categoriesDAO.getAllCategories();
            List<Courses> coursesList = coursesDAO.getAllCourses();
            List<CategoryModel> categoryModelList = new ArrayList<>();
            List<CourseModel> courseModelList = new ArrayList<>();
            for (Categories categories : categoriesList) {
                categoryModelList.add(new CategoryModel(
                        categories.getCategoryId(),
                        categories.getCategoryName(),
                        categories.getCategoryDesc(),
                        coursesDAO.countCourseByCategoryId(categories.getCategoryId()),
                        categories.getCategoryImage()));
            }
            for (Courses courses : coursesList) {
                courseModelList.add(new CourseModel(courses.getCourseId(), courses.getCourseName(), courses.getCourseDesc(), courses.getCoursePrice(), courses.getCourseImage(), courses.getCourseCategory().getCategoryId()));
            }
            model.addAttribute("categories", categoryModelList);
            model.addAttribute("courses", courseModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "welcomePage";
    }

    @RequestMapping(value = {"/course-detail"}, method = RequestMethod.GET)
    public String viewCourseDetail(Model model, @RequestParam(name = "courseid") Long id) {
        try {
            Courses course = coursesDAO.findCourseById(id);
            if (course == null) {
                return "404Page";
            }
            //Lấy ra được categoryId của course hiện tại
            Long categoryId = course.getCourseCategory().getCategoryId();
            //Lấy ra thông tin của CategoriesRaw với id trên
            CategoriesRaw categoriesRaw = categoriesRawDAO.findCategoriesRawById(categoryId);
            //Tạo ra đối tượng CourseDetailModel trả dữ liệu ra view
            CourseDetailModel courseDetailModel = new CourseDetailModel(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseDesc(),
                    course.getCoursePrice(),
                    course.getCourseImage(),
                    categoriesRaw != null ? categoriesRaw.getCategoryName() : null,
                    0,
                    0,
                    null,
                    null
            );
            //Lấy danh sách các Course liên quan
            List<Courses> relateCoursesList = coursesDAO.findCoursesByCategoryIdExceptCourseId(categoryId, course.getCourseId());
            List<CourseModel> courseModelList = new ArrayList<>();
            for (Courses c : relateCoursesList) {
                courseModelList.add(new CourseModel(c.getCourseId(), c.getCourseName(), c.getCourseDesc(), c.getCoursePrice(), c.getCourseImage(), c.getCourseCategory().getCategoryId()));
            }
            //Đưa dữ liệu ra view
            model.addAttribute("course",courseDetailModel);
            model.addAttribute("courses", courseModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "singleCoursePage";
    }

    @RequestMapping("/bao_tri")
    public String BaoTri(Model model) {
        return "500Page";
    }

    @GetMapping("/errorPage")
    public String errorPage(@ModelAttribute(name = "msg") String msg, Model model) {
        model.addAttribute("msg", msg);
        return "errorPage";
    }

    @RequestMapping("/registerSuccessful")
    public String viewRegisterSuccessful() {
        return "registerSuccessfulPage";
    }

    @RequestMapping("/registerSuccessful1")
    public String viewRegisterSuccessful1(Model model, @ModelAttribute("flashUser") Profiles flashUser) {
        try {
            if (flashUser != null) {
                List<CauHinhChung> list = cauHinhChungDAO.getThongTinhCauHinhChungByGroup("CMAIL");
                //
                if (list == null) {
                    model.addAttribute("msgError", "Không tìm thấy thông tin email phục vụ");
                } else {
                    //
                    String mailTitle = "";
                    String mailEnd = "";
                    String mailNoiDung_1 = "";
                    String mailHost = "";
                    String mailTenTruong = "";
                    String mailAddress = "";
                    String mailUrl = "";
                    String tenDangNhap = "USER_NAME";
                    String token = "TOKEN_KEY";
                    String sendTo = "btdung@hcmut.edu.vn";
                    for (CauHinhChung confg : list) {
                        if (confg.getCauHinhNbr().equalsIgnoreCase("TTXN")) {
                            mailTitle = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("ENDM")) {
                            mailEnd = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("CMAIL_ND1")) {
                            mailNoiDung_1 = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("HOST")) {
                            mailHost = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("TTRG")) {
                            mailTenTruong = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("DCTR")) {
                            mailAddress = confg.getCauHinhValue();
                        }
                        if (confg.getCauHinhNbr().equalsIgnoreCase("URLT")) {
                            mailUrl = confg.getCauHinhValue();
                        }
                    }
                    // Noi Dung Email
                    String tmpNoiDung = mailNoiDung_1 + "<br>" + "Tên đăng nhập là: <b>" + tenDangNhap + "</b><br>"
                            + "Vui lòng xác nhận bằng cách nhấn " + "<a href='" + mailHost
                            + "xacNhanEmailXetTuyen.action?token=" + token + "'>tại đây</a><br><br>" + mailEnd
                            + "<br><br>Trân trọng,<br><br>" + mailTenTruong + "<br>" + mailAddress + "<br>" + mailUrl;
                    // Send Email
                    Mail mail = new Mail();
                    mail.setFrom("support.stu@hcmut.edu.vn");
                    mail.setTo(sendTo);
                    mail.setSubject(mailTitle);
                    //
                    MimeMessage message = emailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
                            StandardCharsets.UTF_8.name());
                    helper.setTo(mail.getTo());
                    helper.setText(tmpNoiDung, true);
                    helper.setSubject(mail.getSubject());
                    helper.setFrom(mail.getFrom());
                    this.emailSender.send(message);
                    // SimpleMailMessage message = new SimpleMailMessage();
                    // message.setTo(sendTo);
                    // message.setSubject(mailTitle);
                    // message.setText(tmpNoiDung);
                    // this.emailSender.send(message);
                    // End
                }
            }
        } catch (Exception e) {
            model.addAttribute("msgError", e.getMessage());
        }
        return "registerSuccessfulPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        RegisterForm form = new RegisterForm();
        model.addAttribute("registerForm", form);
        return "registerPage";
    }

    @PostMapping(value = "/register", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MessageResponse saveRegister(Model model, //
                                        @ModelAttribute("registerForm") @Validated RegisterForm registerForm, //
                                        BindingResult result) {
        try {
            if (result.hasErrors()) {
                Map<String, String> errors = result.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

                return new MessageResponse("500", "errors", errors);
            } else {
                Profiles newUser = null;
                newUser = profilesHibernateDAO.createNewProfile(registerForm);
                if (newUser != null) {
                    // Send email
                    List<CauHinhChung> list = cauHinhChungDAO.getThongTinhCauHinhChungByGroup("CMAIL");
                    //
                    if (list == null) {
                        model.addAttribute("msgError", "Không tìm thấy thông tin email phục vụ");
                    } else {
                        // create token
                        String passwd = TextUtils.generatePassword(10);
                        String token = newUser.getCmndSo() + "#" + newUser.getEmail_1() + "#" + passwd;
                        token = encryptAESProvider.encrypt(token);
                        token = URLEncoder.encode(token, StandardCharsets.UTF_8.name());
                        //
                        String mailTitle = "";
                        String mailEnd = "";
                        String mailNoiDung_1 = "";
                        String mailHost = "";
                        String mailTenTruong = "";
                        String mailAddress = "";
                        String mailUrl = "";
                        String tenDangNhap = newUser.getCmndSo();
                        String sendTo = newUser.getEmail_1();
                        for (CauHinhChung confg : list) {
                            if (confg.getCauHinhNbr().equalsIgnoreCase("TTXN")) {
                                mailTitle = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("ENDM")) {
                                mailEnd = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("CMAIL_ND1")) {
                                mailNoiDung_1 = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("HOST")) {
                                mailHost = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("TTRG")) {
                                mailTenTruong = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("DCTR")) {
                                mailAddress = confg.getCauHinhValue();
                            }
                            if (confg.getCauHinhNbr().equalsIgnoreCase("URLT")) {
                                mailUrl = confg.getCauHinhValue();
                            }
                        }
                        // Noi Dung Email
                        String tmpNoiDung = mailNoiDung_1 + "<br>" + "Tên đăng nhập là: <b>" + tenDangNhap + "</b><br>"
                                + "Mật khẩu đăng nhập: <b>" + passwd + "</b><br>" + "Vui lòng xác nhận bằng cách nhấn "
                                + "<a href='" + mailHost + "xacNhanEmail?token=" + token + "'>tại đây</a><br><br>"
                                + mailEnd + "<br><br>Trân trọng,<br><br>" + mailTenTruong + "<br>" + mailAddress
                                + "<br>" + mailUrl;
                        // Send Email
                        Mail mail = new Mail();
                        mail.setFrom("support.stu@hcmut.edu.vn");
                        mail.setTo(sendTo);
                        mail.setSubject(mailTitle);
                        //
                        MimeMessage message = emailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(message,
                                MimeMessageHelper.MULTIPART_MODE_RELATED, StandardCharsets.UTF_8.name());
                        helper.setTo(mail.getTo());
                        helper.setText(tmpNoiDung, true);
                        helper.setSubject(mail.getSubject());
                        helper.setFrom(mail.getFrom());
                        this.emailSender.send(message);
                    }
                    //
                    return new MessageResponse("200", "ok");
                } else {
                    return new MessageResponse("500", "Không tạo được hồ sơ mới");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageResponse("500", e.getMessage());
        }
    }

    @GetMapping("/xacNhanEmail")
    public String xacNhanEmail(@RequestParam(value = "token", required = true) String token,
                               RedirectAttributes redirectAttributes) {
        try {
            // token = URLDecoder.decode(token, StandardCharsets.UTF_8.name());
            String tokenKey = encryptAESProvider.decrypt(token);
            String[] arr = tokenKey.split("#");
            String tenDangNhap = arr[0];
            String email = arr[1];
            String matKhau = arr[2];
            // check actived
            boolean userNameExists = usersRawHibernateDAO.checkExistsUserName(tenDangNhap, 0L);
            if (!userNameExists) {
                // get first name & last name
                ProfilesRaw profile = profilesRawHibernateDAO.getInfoProfile(tenDangNhap);
                if (profile != null) {
                    //
                    Role role = roleHibernateDAO.findRoleByName("USER");
                    UsersForm userForm = new UsersForm();
                    userForm.setActive(true);
                    userForm.setEmail(email);
                    userForm.setFirstName(profile.getHoLot());
                    userForm.setLastName(profile.getTen());
                    userForm.setPasswordLogin(matKhau);
                    userForm.setRole(role.getRoleId());
                    userForm.setUserId(0L);
                    userForm.setUserName(tenDangNhap);
                    User u = userHibernateDAO.createNewUser(userForm);
                    if (u != null) {
                        profilesDAO.updateActiveUserTTCN(u.getUserName());
                    }
                    //
                    return "redirect:/login";
                } else {
                    redirectAttributes.addAttribute("msg", "Không tìm thấy thông tin hồ sơ");
                    return "redirect:/errorPage";
                }
            } else {
                redirectAttributes.addAttribute("msg", "Tài khoản này đã được kích hoạt");
                return "redirect:/errorPage";
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("msg", e.getMessage());
            return "redirect:/errorPage";
        }
    }
}
