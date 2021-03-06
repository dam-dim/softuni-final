package bg.softuni.final_project.model.service;

import bg.softuni.final_project.model.entity.UserRoleEntity;

import java.util.Set;

public class UserServiceModel {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Set<UserRoleEntity> roles;

    public UserServiceModel() {
    }

    public String getId() {
        return id;
    }

    public UserServiceModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserServiceModel setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
