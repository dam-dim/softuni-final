package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class DiveEditBindingModel {
    private String type;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public DiveEditBindingModel() {
    }

    public String getType() {
        return type;
    }

    public DiveEditBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiveEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public DiveEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
