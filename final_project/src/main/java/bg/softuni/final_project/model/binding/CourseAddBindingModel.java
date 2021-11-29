package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.course_name.UniqueCourseName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CourseAddBindingModel {

    private String name;
    private CourseLevelEnum level;
    private String description;

    public CourseAddBindingModel() {
    }

    @UniqueCourseName
    @NotBlank(message = "Course name cannot be blank.")
    public String getName() {
        return name;
    }

    public CourseAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseAddBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public CourseAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
