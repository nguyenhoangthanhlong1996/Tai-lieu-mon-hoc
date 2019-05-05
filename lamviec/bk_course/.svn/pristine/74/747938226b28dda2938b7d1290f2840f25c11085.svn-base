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
@Table(name = "courseenrollment_lst")
public class EnrollmentDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long enrollmentDetailId;
	private String createdTime;
	private int slMax;
	private int stt;
	@Column(name = "courseEnrollmentId")
	private Long enrollmentId;
	private Long courseId;
}
