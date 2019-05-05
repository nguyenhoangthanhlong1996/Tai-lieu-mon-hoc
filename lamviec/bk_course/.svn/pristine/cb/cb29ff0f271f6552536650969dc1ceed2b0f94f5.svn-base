package vn.edu.hcmut.core.model.hibernate;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ttcn")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profiles implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long ttcnId;
	private String hoLot;
	private String ten;
	private String hoVaTen;
	private String ngayThangNamSinh;
	private String ngaySinh;
	private String thangSinh;
	private String namSinh;
	private String gioiTinh;
	private String cmndMatTruoc;
	private String cmndMatSau;
	private String cmndSo;
	private String cmndNgayCap;
	private Long cmndNoiCapId;
	private Long noiSinhTinhTpId;
	private Long taiKhoanId;
	private String noiSinhTinhTpKhac;
	private String email_1;
	private String email_2;
	private String soDienThoai;
	private Long dcttTinhTpId;
	private Long dcttQuanHuyenId;
	private Long dcttPhuongXaId;
	private String dcttSoNhaDuong;
	@Column(insertable=false)
	private Timestamp createdTime;
	private int emailConfirmFlag;
	private Timestamp emailConfirmTime;
}
