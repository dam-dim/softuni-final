package bg.softuni.final_project.model.view;

import bg.softuni.final_project.model.entity.UserRoleEntity;

import java.util.Set;

public class UserViewModel {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private UserRoleEntity role;

    public UserViewModel() {
    }

    public String getId() {
        return id;
    }

    public UserViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public UserViewModel setRole(UserRoleEntity role) {
        this.role = role;
        return this;
    }
}
