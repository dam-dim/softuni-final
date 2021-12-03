package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity{
    private String name;
    private String description;
    private CourseLevelEnum level;
    private String imageUrl;

    public CourseEntity() {
    }

    @Column(name = "name", nullable = false, unique = true)
    @NotNull
    @Size(min = 3, max = 30)
    public String getName() {
        return name;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotNull
    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public CourseEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    public CourseLevelEnum getLevel() {
        return level;
    }

    public CourseEntity setLevel(CourseLevelEnum level) {
        this.level = level;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    @NotNull
    public String getImageUrl() {
        return imageUrl;
    }

    public CourseEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
