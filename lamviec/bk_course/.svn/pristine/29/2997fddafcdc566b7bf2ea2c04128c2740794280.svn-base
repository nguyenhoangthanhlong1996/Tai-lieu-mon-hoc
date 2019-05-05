package vn.edu.hcmut.core.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import vn.edu.hcmut.core.repository.GeneralImpl;

@Repository("generalDAO")
public class GeneralDAO extends JdbcDaoSupport implements GeneralImpl {

	@Autowired
	public GeneralDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public List<Map<String, Object>> danhSachTinhThanhPho() throws Exception {
		try {
			final String strQuerySQL = "select id,tinhTpName as 'text' from tinhtp where hieuLucFlag=1 and id<>101 order by stt";
			return this.getJdbcTemplate().queryForList(strQuerySQL);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> danhSachQuanHuyen(int tinhThanhPhoId) throws Exception {
		try {
			final String strQuerySQL = "select id,quanHuyenName as 'text' from quanhuyen where hieuLucFlag=1 and tinhTpId="
					+ tinhThanhPhoId + " order by stt";
			return this.getJdbcTemplate().queryForList(strQuerySQL);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> danhSachPhuongXa(int quanHuyenId) throws Exception {
		try {
			final String strQuerySQL = "select id,phuongXaName as 'text' from phuongxa where hieuLucFlag=1 and quanHuyenId="
					+ quanHuyenId + " order by stt";
			return this.getJdbcTemplate().queryForList(strQuerySQL);
		} catch (Exception e) {
			throw e;
		}
	}

}
