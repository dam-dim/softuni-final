package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

public class DiveDetailsViewModel {
    private String id;
    private String type;
    private String description;
    private DiveLevelEnum level;
    private String imageUrl;

    public DiveDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public DiveDetailsViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public DiveDetailsViewModel setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DiveDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveDetailsViewModel setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DiveDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
