package bg.softuni.final_project.web;

import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.model.view.CourseViewModel;
import bg.softuni.final_project.service.CourseService;
import bg.softuni.final_project.service.StatsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final StatsService statsService;
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public AdminController(StatsService statsService, CourseService courseService, ModelMapper modelMapper) {
        this.statsService = statsService;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String admin(Model model) {
        model
                .addAttribute("stats", statsService.getStats())
                .addAttribute("courses", mapListToView(courseService.getSortedPopularCourses()));

        return "admin";
    }

    private List<CourseViewModel> mapListToView(List<CourseServiceModel> serviceModels) {
        return serviceModels
                .stream()
                .map(this::mapCourseToView)
                .collect(Collectors.toList());
    }

    private CourseViewModel mapCourseToView(CourseServiceModel courseServiceModel) {
        return modelMapper.map(courseServiceModel, CourseViewModel.class);
    }
}
