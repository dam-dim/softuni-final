package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class CourseEditBindingModel {
    private String name;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public CourseEditBindingModel() {
    }

    public String getName() {
        return name;
    }

    public CourseEditBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
