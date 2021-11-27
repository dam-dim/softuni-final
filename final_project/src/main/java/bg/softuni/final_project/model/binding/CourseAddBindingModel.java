package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CourseAddBindingModel {

    private String name;
    private CourseLevelEnum level;
    private String description;

    public CourseAddBindingModel() {
    }

    @NotNull
    @NotEmpty
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

    @NotNull
    @NotEmpty
    public String getDescription() {
        return description;
    }

    public CourseAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
