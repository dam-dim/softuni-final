package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.course_name.UniqueCourseName;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CourseEditBindingModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public CourseEditBindingModel() {
    }

    public String getId() {
        return id;
    }

    public CourseEditBindingModel setId(String id) {
        this.id = id;
        return this;
    }

    @NotNull(message = "Course name is required.")
    @UniqueCourseName
    @Size(min = 3, max = 30, message = "Course name should be between 3 and 30 characters long.")
    public String getName() {
        return name;
    }

    public CourseEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull(message = "Course description is required.")
    @Size(min = 20, message = "Course description should be minimum 20 characters long.")
    public String getDescription() {
        return description;
    }

    public CourseEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank(message = "Image URL is required.")
    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull(message = "Course level is required.")
    @Enumerated(EnumType.STRING)
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
