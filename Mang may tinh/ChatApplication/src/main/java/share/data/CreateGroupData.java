package share.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreateGroupData implements Serializable {
    String nameGroup;
    String avatarGroup;
    List<String> users;
    private List<String> errors;

    public CreateGroupData() {
        this.errors = new ArrayList<>();
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String getAvatarGroup() {
        return avatarGroup;
    }

    public void setAvatarGroup(String avatarGroup) {
        this.avatarGroup = avatarGroup;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public boolean isValid() {
        errors.clear();
        //Kiểm tra tên nhóm
        if (nameGroup.trim().equals(""))
            errors.add("Tên nhóm không được bỏ trống");
        //Kiểm tra danh sách người trong nhóm
        if (users.isEmpty())
            errors.add("Bạn phải thêm ít nhất 1 người vào nhóm");
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
