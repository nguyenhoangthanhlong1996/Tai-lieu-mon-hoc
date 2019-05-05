package vn.edu.hcmut.core.repository;

import java.util.List;
import java.util.Map;

public interface GeneralImpl {
	List<Map<String, Object>> danhSachTinhThanhPho() throws Exception;
	List<Map<String, Object>> danhSachQuanHuyen(int tinhThanhPhoId) throws Exception;
	List<Map<String, Object>> danhSachPhuongXa(int quanHuyenId) throws Exception;
}
