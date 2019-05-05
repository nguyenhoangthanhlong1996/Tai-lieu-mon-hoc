package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;

public interface ICategoriesRaw {
	List<CategoriesRaw> getAllCategoriesRaw() throws Exception;
}
