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
@Table(name = "coursecategory")
public class Categories implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long categoryId;
	@Column(name = "courseCategoryName")
	private String categoryName;
	@Column(name = "courseCategoryNbr")
	private String categoryNbr;
	@Column(name = "courseCategoryImage")
	private String categoryImage;
	@Column(name = "courseCategoryDesc")
	private String categoryDesc;
	@Column(name = "hieuLucFlag")
	private boolean categoryEnable;
}
