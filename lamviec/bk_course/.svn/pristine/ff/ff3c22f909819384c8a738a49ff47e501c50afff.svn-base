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
@Table(name = "phuongxa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhuongXa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long phuongXaId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quanHuyenId")
	private QuanHuyen quanHuyenId;
	private String phuongXaName;
	private Integer hieuLucFlag;
	private String phuongXaNbr;
	private String phuongXaNbr_Ca;
	private Integer stt;
	private Integer xaNgheoFlag;

}
