package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.form.bkcourse.CategoriesForm;
import vn.edu.hcmut.core.model.hibernate.bkcourse.Categories;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategories;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("categoriesHibernateDAO")
@Transactional(rollbackOn=Exception.class)
public class CategoriesDAO extends HibernateGenericDao<Categories, Long> implements ICategories {

	@Autowired
	public CategoriesDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Categories createNewCategory(CategoriesForm obj) throws Exception {
		try {
			Categories category = new Categories();
			category.setCategoryId(obj.getCategoryId());
			category.setCategoryName(obj.getCategoryName());
			category.setCategoryNbr(obj.getCategoryNbr());
			category.setCategoryDesc(obj.getCategoryDesc());
			category.setCategoryImage(obj.getCategoryImage());
			category.setCategoryEnable(obj.isCategoryEnable());
			Long id = this.save(category);
			category.setCategoryId(id);
			return category;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Categories modifyCategoryExists(CategoriesForm obj) throws Exception {
		try {
			Categories category = new Categories();
			category.setCategoryId(obj.getCategoryId());
			category.setCategoryName(obj.getCategoryName());
			category.setCategoryNbr(obj.getCategoryNbr());
			category.setCategoryDesc(obj.getCategoryDesc());
			category.setCategoryImage(obj.getCategoryImage());
			category.setCategoryEnable(obj.isCategoryEnable());
			this.saveOrUpdate(category);
			return category;
		}catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Categories findCategoryById(Long categoryId) throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("id");
				con.setOperator("eq");
				con.setValue1(categoryId);
				cons.add(con);
			}
			List<Categories> result = this.findByCondition(Categories.class, cons, 0, 0);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Categories> getAllCategories() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("categoryName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<Categories> result = this.findByCondition(Categories.class, cons, 0, 0);
			if (result != null && !result.isEmpty()) {
				return result;
			}
			return null;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Long removeCategoryExists(Long categoryId) throws Exception {
		try {
			this.delete(categoryId);
			return categoryId;
		}catch (Exception e) {
			throw e;
		}
	}

}
