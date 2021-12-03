package bg.softuni.final_project.model.binding;

import bg.softuni.final_project.model.entity.enums.UserRoleEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class AdminBindingModel {
    private UserRoleEnum role;

    public AdminBindingModel() {
    }

    @NotNull(message = "Please choose a role.")
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public AdminBindingModel setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
