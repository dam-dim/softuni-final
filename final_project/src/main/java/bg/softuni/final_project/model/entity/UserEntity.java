package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Set<UserRoleEntity> roles;
    private Set<ReviewEntity> reviews;

    public UserEntity() {
        roles = new HashSet<>();
    }

    @Column(name = "first_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 15)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    @NotNull
    @Size(min = 2, max = 15)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(name = "username", nullable = false, unique = true)
    @NotNull
    @Size(min = 3, max = 15)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password", nullable = false)
    @NotNull
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", nullable = false)
    @NotNull
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    @OneToMany(mappedBy = "author")
    public Set<ReviewEntity> getReviews() {
        return reviews;
    }

    public UserEntity setReviews(Set<ReviewEntity> reviews) {
        this.reviews = reviews;
        return this;
    }
}
