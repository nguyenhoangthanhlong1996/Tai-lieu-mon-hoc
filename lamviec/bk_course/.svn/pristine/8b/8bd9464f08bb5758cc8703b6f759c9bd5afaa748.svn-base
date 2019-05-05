package vn.edu.hcmut.core.model.hibernate;

import java.io.Serializable;

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
@Table(name = "cauhinhchung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CauHinhChung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "cauHinhGroup")
	private String cauHinhGroup;

	@Column(name = "cauHinhName")
	private String cauHinhName;

	@Column(name = "cauHinhNbr")
	private String cauHinhNbr;

	@Column(name = "cauHinhValue")
	private String cauHinhValue;

	@Column(name = "ghiChu")
	private String ghiChu;

	@Column(name = "hieuLucFlag")
	private int hieuLucFlag;
}
