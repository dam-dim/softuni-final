package bg.softuni.final_project.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class ReviewEntity extends BaseEntity{
    private String message;
    private LocalDateTime postedOn;
    private UserEntity author;

    public ReviewEntity() {
        setPostedOn(LocalDateTime.now());
    }

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    public String getMessage() {
        return message;
    }

    public ReviewEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public ReviewEntity setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public ReviewEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
