package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminBindingModel {
    private UserRoleEnum role;

    public AdminBindingModel() {
    }

    @NotNull
    public UserRoleEnum getRole() {
        return role;
    }

    public AdminBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
