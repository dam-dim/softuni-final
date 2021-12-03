package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.validator.dive_name.UniqueDiveType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DiveAddBindingModel {
    private DiveLevelEnum level;
    private String description;
    private String type;

    public DiveAddBindingModel() {
    }

    @NotNull(message = "Dive level is required.")
    @Enumerated(EnumType.STRING)
    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveAddBindingModel setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    @NotNull(message = "Dive description is required.")
    @Size(min = 20, message = "Dive description should be minimum 20 characters long.")
    public String getDescription() {
        return description;
    }

    public DiveAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @UniqueDiveType
    @NotNull(message = "Dive type is required.")
    @Size(min = 3, max = 20, message = "Dive type should be between 3 and 20 characters long.")
    public String getType() {
        return type;
    }

    public DiveAddBindingModel setType(String type) {
        this.type = type;
        return this;
    }
}
