package vn.edu.hcmut.core.dao.hibernate;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.model.hibernate.Breadcrumb;
import vn.edu.hcmut.core.repository.hibernate.IApplicationGeneral;
import vn.edu.hcmut.core.utilities.hibernate.HibernateGenericDao;

@Repository("applicationGeneralDAO")
@Transactional(rollbackOn = Exception.class)
public class ApplicationGeneralDAO extends HibernateGenericDao<Breadcrumb, Long> implements IApplicationGeneral {

	@Autowired
	public ApplicationGeneralDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Breadcrumb getBreadcrumbByModuleName(String moduleName) throws Exception {
		Breadcrumb breadcrumb = new Breadcrumb();
		breadcrumb.setApplicationName("Khóa học ngắn hạn");
		breadcrumb.setModuleName("Quản lý danh mục");
		return breadcrumb;
	}

}
