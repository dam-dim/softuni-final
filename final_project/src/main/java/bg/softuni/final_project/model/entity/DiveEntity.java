package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "dives")
public class DiveEntity extends BaseEntity {
    private String type;
    private String description;
    private DiveLevelEnum level;
    private String imageUrl;

    public DiveEntity() {
    }

    @Column(name = "type", nullable = false, unique = true)
    @NotNull
    @Size(min = 3, max = 20)
    public String getType() {
        return type;
    }

    public DiveEntity setType(String type) {
        this.type = type;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @NotNull
    @Size(min = 20)
    public String getDescription() {
        return description;
    }

    public DiveEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveEntity setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    @NotNull
    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
