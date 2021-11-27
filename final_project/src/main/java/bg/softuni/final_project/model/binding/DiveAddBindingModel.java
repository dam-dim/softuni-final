package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DiveAddBindingModel {
    private DiveLevelEnum level;
    private String description;
    private String type;

    public DiveAddBindingModel() {
    }

    @NotNull
    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveAddBindingModel setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    @NotNull
    @NotEmpty
    public String getDescription() {
        return description;
    }

    public DiveAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @NotEmpty
    public String getType() {
        return type;
    }

    public DiveAddBindingModel setType(String type) {
        this.type = type;
        return this;
    }
}
