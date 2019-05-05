package vn.edu.hcmut.core.model.hibernate.bkcourse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "courseenrollment")
public class Enrollments implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long courseEnrollmentId;
	private String courseEnrollmentName;
	private String courseEnrollmentNbr;
	private String courseEnrollmentDesc;
	@Column(name = "hieuLucFlag")
	private boolean courseEnrollmentEnable;
	private String createdTime;
	@Column(name = "thoiGianDky_Bd")
	private String courseEnrollmentBegin;
	@Column(name = "thoiGianDky_Kt")
	private String courseEnrollmentEnd;
}
