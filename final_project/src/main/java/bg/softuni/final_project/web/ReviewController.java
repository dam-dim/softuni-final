package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.ReviewAddBindingModel;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.service.ReviewServiceModel;
import bg.softuni.final_project.model.view.ReviewViewModel;
import bg.softuni.final_project.service.ReviewService;
import bg.softuni.final_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewController.class);

    private final ReviewService reviewService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public ReviewController(ReviewService reviewService, UserService userService, ModelMapper modelMapper) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String reviews(Model model, Principal principal) {
        model.addAttribute("reviews", mapServiceToView(reviewService.findAll(), principal));

        return "reviews";
    }

    private List<ReviewViewModel> mapServiceToView(List<ReviewServiceModel> serviceModels, Principal principal) {
        return serviceModels
                .stream()
                .map(reviewServiceModel -> mapToView(reviewServiceModel, principal))
                .collect(Collectors.toList());
    }

    private ReviewViewModel mapToView(ReviewServiceModel reviewServiceModel, Principal principal) {
        ReviewViewModel viewModel = new ReviewViewModel();
        UserEntity author = userService.findById(reviewServiceModel.getAuthorId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:SS");

        viewModel
                .setMessage(reviewServiceModel.getMessage())
                .setPostedOn(reviewServiceModel.getPostedOn().format(formatter))
                .setId(reviewServiceModel.getReviewId())
                .setFirstName(author.getFirstName())
                .setLastName(author.getLastName())
                .setUsername(author.getUsername());

        if (principal == null) {
            viewModel.setOwner(false);
        } else {
            viewModel.setOwner(reviewService.isOwner(principal.getName(), viewModel.getId()));
        }

        return viewModel;
    }

    @Secured({"ROLE_USER"})
    @GetMapping("/add")
    public String add() {
        return "review-add";
    }

    @Secured({"ROLE_USER"})
    @PostMapping("/add")
    public String addConfirm(@Valid ReviewAddBindingModel reviewAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @AuthenticationPrincipal UserDetails principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("reviewAddBindingModel", reviewAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.reviewAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        reviewService.addReview(bindingToService(reviewAddBindingModel, principal.getUsername()));

        return "redirect:/reviews";
    }

    private ReviewServiceModel bindingToService(ReviewAddBindingModel bindingModel, String authorUsername) {
        ReviewServiceModel serviceModel = modelMapper.map(bindingModel, ReviewServiceModel.class);
        serviceModel
                .setAuthorId(userService.findUserByUsername(authorUsername).getId());

        return serviceModel;
    }

    @PreAuthorize("@reviewServiceImpl.canDelete(#principal.name, #id)")
    @DeleteMapping("/{id}/delete")
    public String deleteConfirm(@PathVariable String id, Principal principal) {
        reviewService.deleteReview(id);
        return "redirect:/reviews";
    }

    @ModelAttribute
    public ReviewAddBindingModel reviewAddBindingModel() {
        return new ReviewAddBindingModel();
    }
}
