package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.validator.dive_name.UniqueDiveType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DiveEditBindingModel {
    private String type;
    private String description;
    private String imageUrl;
    private CourseLevelEnum level;

    public DiveEditBindingModel() {
    }

    @UniqueDiveType
    @NotBlank(message = "Cannot be empty.")
    public String getType() {
        return type;
    }

    public DiveEditBindingModel setType(String type) {
        this.type = type;
        return this;
    }

    @NotBlank
    public String getDescription() {
        return description;
    }

    public DiveEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotBlank
    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEditBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @NotNull
    public CourseLevelEnum getLevel() {
        return level;
    }

    public DiveEditBindingModel setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }
}
