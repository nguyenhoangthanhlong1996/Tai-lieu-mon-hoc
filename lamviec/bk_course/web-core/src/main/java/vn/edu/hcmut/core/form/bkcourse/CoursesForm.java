package vn.edu.hcmut.core.form.bkcourse;

import lombok.Data;

@Data
public class CoursesForm {
	private Long courseId;
	private String courseName;
	private String courseNbr;
	private String courseLearningHour;
	private String coursePrice;
	private boolean courseEnable;
	private String courseDesc;
	private Long courseCategory;
}
