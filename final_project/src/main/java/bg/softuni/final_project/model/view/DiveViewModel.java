package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

public class DiveViewModel {
    private String id;
    private String type;
    private String description;
    private DiveLevelEnum level;
    private String imageUrl;

    public DiveViewModel() {
    }

    public String getId() {
        return id;
    }

    public DiveViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public DiveViewModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiveViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveViewModel setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DiveViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
