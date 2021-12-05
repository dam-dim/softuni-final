package bg.softuni.final_project.model.service;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class CourseServiceModel {
    private String id;
    private String name;
    private String description;
    private CourseLevelEnum level;
    private String imageUrl;
    private int clicks;

    public CourseServiceModel() {
    }

    public String getId() {
        return id;
    }

    public CourseServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseServiceModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getClicks() {
        return clicks;
    }

    public CourseServiceModel setClicks(int clicks) {
        this.clicks = clicks;
        return this;
    }
}
