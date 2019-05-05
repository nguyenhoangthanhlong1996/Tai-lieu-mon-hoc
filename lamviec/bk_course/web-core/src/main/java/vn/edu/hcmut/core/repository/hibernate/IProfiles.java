package vn.edu.hcmut.core.repository.hibernate;

import vn.edu.hcmut.core.form.RegisterForm;
import vn.edu.hcmut.core.model.hibernate.Profiles;

public interface IProfiles {
	Profiles createNewProfile(RegisterForm formData) throws Exception;
}
