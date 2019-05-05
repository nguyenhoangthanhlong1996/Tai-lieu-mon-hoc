//package vn.edu.hcmut.core.utilities;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.core.support.JdbcDaoSupport;
//import org.springframework.stereotype.Component;
//
//@Component("mysqlProvider")
//public class MySQLProvider extends JdbcDaoSupport {
//	public MySQLProvider(DataSource dataSource) {
//		this.setDataSource(dataSource);
//	}
//	
//	public List<Map<String, Object>> ExecuteStoreProduceByParameterReturnList(String storeProduceName, Object[] params, int[] paramType)
//			throws Exception {
//		try {
//			String sql_  = "call " + storeProduceName + "(";
//			for (int i = 0; i < params.length - 1; i++) {
//				sql_ += "?,";
//			}
//			sql_ += "?)";
//			return this.getJdbcTemplate().queryForList(sql_, params, paramType);
//		} catch (Exception e) {
//			throw e;
//		}
//	}
//	
//	public List<Map<String, Object>> ExecuteQueryReturnList(String sql)
//			throws Exception {
//		try {
//			return this.getJdbcTemplate().queryForList(sql);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}
//	}
//}
