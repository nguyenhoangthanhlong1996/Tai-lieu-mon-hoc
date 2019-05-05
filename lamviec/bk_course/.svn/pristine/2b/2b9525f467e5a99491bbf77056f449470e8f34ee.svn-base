package vn.edu.hcmut.core.dao.hibernate.bkcourse;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.bkcourse.CategoriesRaw;
import vn.edu.hcmut.core.repository.hibernate.bkcourse.ICategoriesRaw;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("categoriesRawHibernateDAO")
@Transactional(rollbackOn=Exception.class)
public class CategoriesRawDAO extends HibernateGenericDao<CategoriesRaw, Long> implements ICategoriesRaw {

	@Autowired
	public CategoriesRawDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CategoriesRaw> getAllCategoriesRaw() throws Exception {
		try {
			List<ConditionReport> cons = new ArrayList<ConditionReport>();
			{
				ConditionReport con = new ConditionReport();
				con.setCol("categoryName");
				con.setOperator("order");
				con.setValue1("asc");
				cons.add(con);
			}
			List<CategoriesRaw> result = this.findByCondition(CategoriesRaw.class, cons, 0, 0);
			if (result != null && !result.isEmpty()) {
				return result;
			}
			return null;
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}
}
