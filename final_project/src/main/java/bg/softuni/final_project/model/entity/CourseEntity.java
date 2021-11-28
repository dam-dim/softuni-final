package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity{
    private String name;
    private String description;
    private CourseLevelEnum level;
    private String imageUrl;
    private List<UserEntity> users;

    public CourseEntity() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CourseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEntity setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany(mappedBy = "courses")
    public List<UserEntity> getUsers() {
        return users;
    }

    public CourseEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }
}
