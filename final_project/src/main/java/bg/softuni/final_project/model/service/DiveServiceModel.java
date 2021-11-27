package bg.softuni.final_project.model.service;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

public class DiveServiceModel {
    private String id;
    private String type;
    private String description;
    private DiveLevelEnum level;
    private String imageUrl;

    public DiveServiceModel() {
    }

    public String getId() {
        return id;
    }

    public DiveServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public DiveServiceModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiveServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveServiceModel setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DiveServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
