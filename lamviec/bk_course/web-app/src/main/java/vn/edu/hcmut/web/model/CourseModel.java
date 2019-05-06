package vn.edu.hcmut.web.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseModel {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private Long categoryId;
}
