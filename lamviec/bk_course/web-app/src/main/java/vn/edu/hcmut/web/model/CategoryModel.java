package vn.edu.hcmut.web.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryModel {
    private Long id;
    private String name;
    private String description;
    private int totalCourse;
    private String image;
}
