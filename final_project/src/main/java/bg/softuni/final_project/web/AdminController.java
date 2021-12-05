package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.AdminBindingModel;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.model.service.UserServiceModel;
import bg.softuni.final_project.model.view.CourseViewModel;
import bg.softuni.final_project.model.view.UserViewModel;
import bg.softuni.final_project.service.CourseService;
import bg.softuni.final_project.service.StatsService;
import bg.softuni.final_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final StatsService statsService;
    private final CourseService courseService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AdminController(StatsService statsService, CourseService courseService, UserService userService, ModelMapper modelMapper) {
        this.statsService = statsService;
        this.courseService = courseService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String admin(Model model) {
        model
                .addAttribute("stats", statsService.getStats())
                .addAttribute("courses", mapCourseListToView(courseService.getSortedPopularCourses()))
                .addAttribute("allNotAdmin", mapUserListToView(userService.findAllNotAdmin()))
                .addAttribute("roles", UserRoleEnum.values());
        return "admin";
    }

    @Secured({"ROLE_ADMIN"})
    @PatchMapping("/{id}")
    public String changeRole(@Valid AdminBindingModel adminBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             @PathVariable String id) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("adminBindingModel", adminBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.adminBindingModel", bindingResult);

            return "redirect:/admin";
        }

        userService.updateUserRole(id, adminBindingModel.getRole());

        return "redirect:/admin";
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @ModelAttribute
    public AdminBindingModel adminBindingModel() {
        return new AdminBindingModel();
    }

    // maps the provided list with service models to list with view models FOR COURSES
    private List<CourseViewModel> mapCourseListToView(List<CourseServiceModel> serviceModels) {
        return serviceModels
                .stream()
                .map(this::mapCourseToView)
                .collect(Collectors.toList());
    }

    //maps the provided service model to view model FOR COURSES
    private CourseViewModel mapCourseToView(CourseServiceModel courseServiceModel) {
        return modelMapper.map(courseServiceModel, CourseViewModel.class);
    }

    // maps the provided list with service models to list with view models FOR USERS
    private List<UserViewModel> mapUserListToView(List<UserServiceModel> serviceModels) {
        return serviceModels
                .stream()
                .map(this::mapUserToView)
                .collect(Collectors.toList());
    }

    //maps the provided service model to view model FOR USERS
    private UserViewModel mapUserToView(UserServiceModel userServiceModel) {
        UserViewModel viewModel = modelMapper.map(userServiceModel, UserViewModel.class);
        viewModel.setRole(userServiceModel.getRoles().stream().findFirst().orElse(null));
        return viewModel;
    }
}
