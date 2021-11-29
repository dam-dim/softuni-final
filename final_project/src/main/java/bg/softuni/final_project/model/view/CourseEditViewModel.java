package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class CourseEditViewModel {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public CourseEditViewModel() {
    }

    public String getId() {
        return id;
    }

    public CourseEditViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseEditViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEditViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEditViewModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
