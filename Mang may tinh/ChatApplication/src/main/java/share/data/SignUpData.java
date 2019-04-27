package share.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SignUpData implements Serializable {
    private String username;
    private String name;
    private boolean gender;
    private String password;
    private String retypePassword;
    private String avatar; //Lưu dạng base64

    private List<String> errors;

    public SignUpData() {
        this.errors = new ArrayList<>();
    }

    public SignUpData(String username, String name, boolean gender, String password, String retypePassword, String avatar) {
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.retypePassword = retypePassword;
        this.avatar = avatar;
        this.errors = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "SignUpData{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", retypePassword='" + retypePassword + '\'' +
                //", avatar='" + avatar + '\'' +
                ", avatar='" + "base64_encode" + '\'' +
                '}';
    }

    public boolean isValid() {
        errors.clear();
        //Kiểm tra username
        String patternUsername = "^[a-zA-Z][a-zA-Z0-9]{0,39}";
        if (username.equals(""))
            errors.add("Tên tài khoản không được bỏ trống");
        else if (!username.matches(patternUsername))
            errors.add("Tên tài khoản phải từ 1-40 kí tự, chỉ chứa chữ cái và số, phải bắt đầu bởi chữ cái");
        //Kiểm tra tên
        if (name.equals(""))
            errors.add("Tên không được bỏ trống");
        else if (name.length() < 1 || name.length() > 50)
            errors.add("Tên phải từ 1-50 kí tự");
        //Kiểm tra password
        String patternPassword = "[a-zA-Z0-9]{1,40}";
        if (password.equals(""))
            errors.add("Mật khẩu không được bỏ trống");
        else if (!password.matches(patternPassword))
            errors.add("Mật khẩu phải từ 1-40 kí tự, chỉ chứa chữ cái và số");
        else if (!retypePassword.equals(password))
            errors.add("Nhập lại mật khẩu chưa đúng");

        //Trả về kết quả kiểm tra
        if (errors.size() > 0)
            return false;
        else
            return true;
    }

    public List<String> getErrors() {
        return errors;
    }
}
