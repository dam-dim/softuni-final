package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class CourseDetailsViewModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public CourseDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public CourseDetailsViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseDetailsViewModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
