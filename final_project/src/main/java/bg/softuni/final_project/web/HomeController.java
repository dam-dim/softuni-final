package bg.softuni.final_project.web;

import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.model.view.CourseViewModel;
import bg.softuni.final_project.service.CourseService;
import bg.softuni.final_project.service.WeatherService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class HomeController {
    private final CourseService courseService;
    private final WeatherService weatherService;
    private final ModelMapper modelMapper;

    private static final int N_TOP_COURSES = 3;

    public HomeController(CourseService courseService, WeatherService weatherService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.weatherService = weatherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<CourseViewModel> courseViewModels = mapListToView(courseService.getTopNPopularCourses(N_TOP_COURSES));

        model
                .addAttribute("topCourses", courseViewModels)
                .addAttribute("weather", weatherService.groupWeatherDto());

        return "index";
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