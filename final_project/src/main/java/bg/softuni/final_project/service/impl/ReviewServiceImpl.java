package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.ReviewEntity;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.ReviewServiceModel;
import bg.softuni.final_project.repository.ReviewRepository;
import bg.softuni.final_project.repository.UserRoleRepository;
import bg.softuni.final_project.service.ReviewService;
import bg.softuni.final_project.service.UserService;
import bg.softuni.final_project.web.exception.ReviewNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ReviewServiceImpl implements ReviewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserService userService, ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<ReviewServiceModel> findAll() {
        List<ReviewEntity> all = reviewRepository.findAll();
        List<ReviewServiceModel> reviewServiceModels = new ArrayList<>();

        for (ReviewEntity reviewEntity : all) {
            ReviewServiceModel reviewServiceModel = modelMapper.map(reviewEntity, ReviewServiceModel.class);
            reviewServiceModel
                    .setReviewId(reviewEntity.getId())
                    .setAuthorId(reviewEntity.getAuthor().getId());
            reviewServiceModels.add(reviewServiceModel);
        }

        return reviewServiceModels;
    }

    @Override
    public void addReview(ReviewServiceModel reviewServiceModel) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity
                .setAuthor(userService.findById(reviewServiceModel.getAuthorId()))
                .setMessage(reviewServiceModel.getMessage());

        reviewRepository.save(reviewEntity);
    }

    @Override
    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewServiceModel findById(String id) {
        return modelMapper.map(reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with id '" + id + "' not found.")),
                ReviewServiceModel.class);
    }

    @Override
    public boolean isOwner(String authorName, String reviewId) {
        UserEntity userEntity = userService.findUserByUsername(authorName);

        return userEntity.getId()
                .equals(reviewRepository
                        .findById(reviewId)
                        .orElseThrow(() -> new ReviewNotFoundException("Review with id '"+reviewId+"' not found."))
                        .getAuthor()
                        .getId());
    }

    @Override
    public void initialiseReviews() {
        if (reviewRepository.count() == 0) {
            List<UserEntity> users = userService.findAllUsersWithRole(UserRoleEnum.USER);
            Random random = new Random();

            for (int i = 0; i < 8; i++) {
                ReviewEntity reviewEntity = new ReviewEntity()
                        .setAuthor(users.get(random.nextInt(users.size())))
                        .setMessage("Very nice services!");
                reviewRepository.save(reviewEntity);
            }
        }
    }

    @Override
    public boolean canDelete(String name, String id) {
        return isOwner(name, id);
    }
}
