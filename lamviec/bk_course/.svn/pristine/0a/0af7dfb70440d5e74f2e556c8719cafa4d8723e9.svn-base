package vn.edu.hcmut.core.dao.bkcourse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.repository.bkcourse.IBuyPaymentCourses;

@Repository("buyPaymentCourses")
public class BuyPaymentCourses extends JdbcDaoSupport implements IBuyPaymentCourses {
	@Autowired
	public BuyPaymentCourses(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public void BuyCourse(final String userName, final Long CourseId) throws Exception {
		try {
			//
			List<SqlParameter> params = new ArrayList<SqlParameter>();
			params.add(new SqlParameter("p_UserName", Types.VARCHAR));
			params.add(new SqlParameter("p_CourseId", Types.INTEGER));
			params.add(new SqlOutParameter("p_CodeError", Types.VARCHAR));
			params.add(new SqlOutParameter("p_MsgError", Types.VARCHAR));
			final String sql_ = "{call sp__BuyCourse(?,?,?,?)}";
			Map<String, Object> resultMap = this.getJdbcTemplate().call(new CallableStatementCreator() {
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection.prepareCall(sql_);
					callableStatement.setString(1, userName);
					callableStatement.setLong(2, CourseId);
					callableStatement.registerOutParameter(3, Types.VARCHAR);
					callableStatement.registerOutParameter(4, Types.VARCHAR);
					return callableStatement;
				}
			}, params);
			String errorCode = resultMap.get("p_CodeError").toString();
			String msgText = resultMap.get("p_MsgError").toString();
			if (errorCode.equals("500")) {
				throw new Exception(msgText);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> getListCourseInCart(String userName) throws Exception {
		try {
			final String sql_ = "call sp__GetListCourseInCart(?)";
			Object[] params_ = new Object[] { userName };
			int[] paramTypes_ = new int[] { Types.VARCHAR };
			return this.getJdbcTemplate().queryForList(sql_, params_, paramTypes_);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteCourseInCart(String userName, Long CartId) throws Exception {
		try {
			final String sql_ = "call sp__DeleteCourseInCart('" + userName + "'," + CartId + ")";
			this.getJdbcTemplate().execute(sql_);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void successRegister(String userName) throws Exception {
		try {
			final String sql_ = "call sp__SuccessRegister('" + userName + "')";
			this.getJdbcTemplate().execute(sql_);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> getListPayment(String userName) throws Exception {
		try {
			final String sql_ = "call sp__GetListPayment(?)";
			Object[] params_ = new Object[] { userName };
			int[] paramTypes_ = new int[] { Types.VARCHAR };
			return this.getJdbcTemplate().queryForList(sql_, params_, paramTypes_);
		} catch (Exception e) {
			throw e;
		}
	}
}
