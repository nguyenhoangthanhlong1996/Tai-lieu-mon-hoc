package vn.edu.hcmut.core.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.repository.IProfiles;

@Repository("profilesDAO")
public class ProfilesDAO extends JdbcDaoSupport implements IProfiles {

	@Autowired
	public ProfilesDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public void updateActiveUserTTCN(String userName) throws Exception {
		try {
			String sqlQuery = "call sp__UpdateActiveUserTTCN('" + userName + "')";
			this.getJdbcTemplate().execute(sqlQuery);
		} catch (Exception e) {
			throw e;
		}
	}
}
