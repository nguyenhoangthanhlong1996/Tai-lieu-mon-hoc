package vn.edu.hcmut.core.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginForm {
	private String userName;
	private String password;
	private boolean remember_me;
}
