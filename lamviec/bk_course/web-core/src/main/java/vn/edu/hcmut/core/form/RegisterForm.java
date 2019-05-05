package vn.edu.hcmut.core.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
	private String userId;
	private String fullName;
	private String gender;
	private String birthDate;
	private String birthPlace;
	private String birthPlaceOther;
	private String phone;
	private String idPassport;
	private String idPassportRetype;
	private String idPassportDate;
	private String idPassportPlace;
	private String idPassportImageFront;
	private String idPassportImageBack;
	private String email;
	private String emailRetype;
	private String cityPlace;
	private String districtPlace;
	private String wardPlace;
	private String streetPlace;
}
