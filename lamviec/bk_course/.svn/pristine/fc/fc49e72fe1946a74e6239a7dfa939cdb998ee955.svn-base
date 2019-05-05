package vn.edu.hcmut.core.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quanhuyen")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuanHuyen implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column( name = "id")
	private Long quanHuyenId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tinhTpId")
	private TinhThanhPho tinhThanhPhoId;
	private String quanHuyenNbr;
	private String quanHuyenNbr_Ca;
	private String quanHuyenName;
	private Integer huyenNgheoFlag;
	private Integer hieuLucFlag;
	private Integer stt;
}
