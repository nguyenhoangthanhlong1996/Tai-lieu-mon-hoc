package vn.edu.hcmut.core.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.CauHinhChung;
import vn.edu.hcmut.core.repository.hibernate.CauHinhChungImpl;
import vn.edu.hcmut.core.utilities.hibernate.ConditionReport;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("cauHinhChungDAO")
@Transactional(rollbackOn = Exception.class)
public class CauHinhChungDAO extends HibernateGenericDao<CauHinhChung, Long> implements CauHinhChungImpl {

	@Autowired
	public CauHinhChungDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CauHinhChung getThongTinCauHinhChungByAlias(String alias) throws Exception {
		List<ConditionReport> cons = new ArrayList<ConditionReport>();
		{
			ConditionReport con = new ConditionReport();
			con.setCol("cauHinhNbr");
			con.setOperator("eq");
			con.setValue1(alias);
			cons.add(con);
		}
		List<CauHinhChung> result = this.findByCondition(CauHinhChung.class, cons, 0, 0);
		if (result.isEmpty())
			return null;
		return result.get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<CauHinhChung> getThongTinhCauHinhChungByGroup(String group) throws Exception {
		List<ConditionReport> cons = new ArrayList<ConditionReport>();
		{
			ConditionReport con = new ConditionReport();
			con.setCol("cauHinhGroup");
			con.setOperator("eq");
			con.setValue1(group);
			cons.add(con);
		}
		List<CauHinhChung> result = this.findByCondition(CauHinhChung.class, cons, 0, 0);
		if (result.isEmpty())
			return null;
		return result;
	}

}
