package bg.softuni.final_project.service;

import bg.softuni.final_project.model.service.ReviewServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReviewService {
    List<ReviewServiceModel> findAll();

    void addReview(ReviewServiceModel reviewServiceModel);

    void deleteReview(String id);

    ReviewServiceModel findById(String id);

    boolean isOwner(String authorName, String reviewId);

    void initialiseReviews();

    boolean canDelete(String name, String id);
}
