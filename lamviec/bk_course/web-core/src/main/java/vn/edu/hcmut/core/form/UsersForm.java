package vn.edu.hcmut.core.form;

import lombok.Data;

@Data
public class UsersForm {
	private Long userId;
	private String userName;
	private String email;
	private String passwordLogin;
	private String firstName;
	private String lastName;
	private boolean active;
	private Long role;
}
