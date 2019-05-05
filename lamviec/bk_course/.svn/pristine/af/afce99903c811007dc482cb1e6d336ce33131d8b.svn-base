package vn.edu.hcmut.core.model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tinhtp")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TinhThanhPho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long tinhThanhPhoId;
	
	private String tinhTpNbr;
	private String tinhTpNbr_Ca;
	private String tinhTpName;
	private Integer stt;
	private Integer hieuLucFlag;
}
