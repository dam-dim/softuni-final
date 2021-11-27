package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity{
    private UserRoleEnum role;

    public UserRoleEntity() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}
