package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

public class DiveEditViewModel {
    private String id;
    private String type;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public DiveEditViewModel() {
    }

    public String getId() {
        return id;
    }

    public DiveEditViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public DiveEditViewModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiveEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEditViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public CourseLevelEnum getLevel() {
        return level;
    }

    public DiveEditViewModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
