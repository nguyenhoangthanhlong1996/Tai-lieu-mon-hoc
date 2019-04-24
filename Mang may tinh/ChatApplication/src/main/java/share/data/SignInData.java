package share.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SignInData implements Serializable {
    private String username;
    private String password;

    private List<String> errors;

    public SignInData() {
        this.errors = new ArrayList<>();
    }

    public SignInData(String username, String password) {
        this.username = username;
        this.password = password;
        this.errors = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignInData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public boolean isValid() {
        errors.clear();
        //Kiểm tra username
        String patternUsername = "^[a-zA-Z][a-zA-Z0-9]{5,39}";
        if (username.equals(""))
            errors.add("Tên tài khoản không được bỏ trống");
        else if (!username.matches(patternUsername))
            errors.add("Tên tài khoản phải từ 6-40 kí tự, chỉ chứa chữ cái và số, phải bắt đầu bởi chữ cái");
        //Kiểm tra password
        String patternPassword = "[a-zA-Z0-9]{6,40}";
        if (password.equals(""))
            errors.add("Mật khẩu không được bỏ trống");
        else if (!password.matches(patternPassword))
            errors.add("Mật khẩu phải từ 6-40 kí tự, chỉ chứa chữ cái và số");
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
