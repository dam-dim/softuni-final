package bg.softuni.final_project.model.service;

import java.time.LocalDateTime;

public class ReviewServiceModel {
    private String reviewId;
    private String authorId;
    private String message;
    private LocalDateTime postedOn;

    public ReviewServiceModel() {
    }

    public String getReviewId() {
        return reviewId;
    }

    public ReviewServiceModel setReviewId(String reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    public String getAuthorId() {
        return authorId;
    }

    public ReviewServiceModel setAuthorId(String authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReviewServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public ReviewServiceModel setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
        return this;
    }
}
