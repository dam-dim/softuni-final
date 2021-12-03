package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.course_name.UniqueCourseName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseAddBindingModel {

    private String name;
    private CourseLevelEnum level;
    private String description;

    public CourseAddBindingModel() {
    }

    @UniqueCourseName
    @NotNull(message = "Course name is required.")
    @Size(min = 3, max = 30, message = "Course name should be between 3 and 30 characters long.")
    public String getName() {
        return name;
    }

    public CourseAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Course level is required.")
    @Enumerated(EnumType.STRING)
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseAddBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }

    @NotNull(message = "Course description is required.")
    @Size(min = 20, message = "Course description should be minimum 20 characters long.")
    public String getDescription() {
        return description;
    }

    public CourseAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
