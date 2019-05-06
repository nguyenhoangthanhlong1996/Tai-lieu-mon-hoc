package vn.edu.hcmut.core.model.hibernate.bkcourse;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "course")
@Data
public class Courses implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long courseId;
	private String courseName;
	private String courseNbr;
	private String courseImage;
	@Column(name = "learningHour")
	private String courseLearningHour;
	@Column(name = "price")
	private Double coursePrice;
	@Column(name = "hieuLucFlag")
	private boolean courseEnable;
	private String courseDesc;
	private String createdTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courseCategoryId", referencedColumnName = "id")
	private Categories courseCategory;
}
