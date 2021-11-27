package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.UserRoleEnum;

import javax.persistence.*;
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
    private Set<UserRoleEntity> roles = new HashSet<>();
    private List<DiveEntity> dives = new ArrayList<>();
    private List<CourseEntity> courses = new ArrayList<>();

    public UserEntity() {
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @ManyToMany
    public List<DiveEntity> getDives() {
        return dives;
    }

    public UserEntity setDives(List<DiveEntity> dives) {
        this.dives = dives;
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

    @ManyToMany
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public UserEntity setCourses(List<CourseEntity> courses) {
        this.courses = courses;
        return this;
    }
}
