package vn.edu.hcmut.core.repository.hibernate.bkcourse;

import java.util.List;

import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;

public interface ICategoriesRaw {
	List<CategoriesRaw> getAllCategoriesRaw() throws Exception;
	CategoriesRaw findCategoriesRawById(Long id) throws Exception;
}
