package vn.edu.hcmut.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmut.core.enums.Errors;
import vn.edu.hcmut.core.model.MessageResponse;
import vn.edu.hcmut.core.repository.GeneralImpl;

@RestController
@RequestMapping("/api/")
// @PreAuthorize("hasAnyRole('MAN', 'ADMIN')")
public class RestfulController {

	@Autowired
	GeneralImpl generalDAO;

	@GetMapping("getDanhSachTinh_ThanhPho")
	public MessageResponse danhSachTinhThanhPho() {
		try {
			List<Map<String, Object>> resultSet = generalDAO.danhSachTinhThanhPho();
			return new MessageResponse(Errors.OK.getCode(), Errors.OK.getText(), resultSet);
		} catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}

	@GetMapping("getDanhSachQuan_Huyen")
	public MessageResponse danhSachQuanHuyen(@RequestParam(name = "id", required = true) int tinhThanhPhoId) {
		try {
			List<Map<String, Object>> resultSet = generalDAO.danhSachQuanHuyen(tinhThanhPhoId);
			return new MessageResponse(Errors.OK.getCode(), Errors.OK.getText(), resultSet);
		} catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
	
	@GetMapping("getDanhSachPhuong_Xa")
	public MessageResponse danhSachPhuongXa(@RequestParam(name = "id", required = true) int quanHuyenId) {
		try {
			List<Map<String, Object>> resultSet = generalDAO.danhSachPhuongXa(quanHuyenId);
			return new MessageResponse(Errors.OK.getCode(), Errors.OK.getText(), resultSet);
		} catch (Exception e) {
			return new MessageResponse("500", e.getMessage());
		}
	}
}
