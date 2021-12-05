package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.dive_name.UniqueDiveType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DiveEditBindingModel {
    private String id;
    private String type;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public DiveEditBindingModel() {
    }

    public String getId() {
        return id;
    }

    public DiveEditBindingModel setId(String id) {
        this.id = id;
        return this;
    }

    @UniqueDiveType
    @NotNull(message = "Dive type is required.")
    @Size(min = 3, max = 30, message = "Dive type should be between 3 and 20 characters long.")
    public String getType() {
        return type;
    }

    public DiveEditBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    @NotNull(message = "Dive description is required.")
    @Size(min = 20, message = "Dive description should be minimum 20 characters long.")
    public String getDescription() {
        return description;
    }

    public DiveEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank(message = "Image URL is required.")
    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull(message = "Dive level is required.")
    @Enumerated(EnumType.STRING)
    public CourseLevelEnum getLevel() {
        return level;
    }

    public DiveEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
