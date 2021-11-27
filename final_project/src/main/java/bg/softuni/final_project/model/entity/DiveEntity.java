package bg.softuni.final_project.model.entity;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dives")
public class DiveEntity extends BaseEntity {
    private String type;
    private String description;
    private DiveLevelEnum level;
    private String imageUrl;
    private List<UserEntity> users;

    public DiveEntity() {
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public DiveEntity setType(String type) {
        this.type = type;
        return this;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public DiveEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(name = "level", nullable = false)
    @Enumerated(EnumType.STRING)
    public DiveLevelEnum getLevel() {
        return level;
    }

    public DiveEntity setLevel(DiveLevelEnum level) {
        this.level = level;
        return this;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public DiveEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany(mappedBy = "dives")
    public List<UserEntity> getUsers() {
        return users;
    }

    public DiveEntity setUsers(List<UserEntity> users) {
        this.users = users;
        return this;
    }
}
