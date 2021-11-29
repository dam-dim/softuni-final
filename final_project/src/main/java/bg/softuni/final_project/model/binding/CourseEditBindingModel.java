package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.course_name.UniqueCourseName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseEditBindingModel {
    private String name;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public CourseEditBindingModel() {
    }

    @NotBlank(message = "Course name cannot be blank.")
    @UniqueCourseName
    public String getName() {
        return name;
    }

    public CourseEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public CourseEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
