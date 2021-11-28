package bg.softuni.final_project.web;

import  bg.softuni.final_project.model.binding.CourseAddBindingModel;
import bg.softuni.final_project.model.binding.CourseEditBindingModel;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.model.view.CourseDetailsViewModel;
import bg.softuni.final_project.model.view.CourseEditViewModel;
import bg.softuni.final_project.model.view.CourseViewModel;
import bg.softuni.final_project.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/services/courses")
public class CoursesController {
    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CoursesController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }

    //--------------------------------
    //------------->HOME<-------------
    // -------------------------------
    @GetMapping
    public String courses(Model model) {
        Map<String, List<CourseViewModel>> courses = new LinkedHashMap<>();

        for (CourseLevelEnum levelEnum : CourseLevelEnum.values()) {
            courses.put(levelEnum.name().toLowerCase(),
                    courseService
                            .findAllByLevel(levelEnum)
                            .stream()
                            .map(courseServiceModel -> modelMapper.map(courseServiceModel, CourseViewModel.class))
                            .collect(Collectors.toList()));
        }

        model.addAttribute("courses", courses);
        return "courses";
    }

    //-----------------------------------
    //------------->DETAILS<-------------
    //-----------------------------------
    //todo: fix the design for details page
    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {

        model.addAttribute("course",
                modelMapper.map(courseService.findById(id), CourseDetailsViewModel.class));

        return "course-details";
    }

    @PostMapping("/details/{id}")
    public String detailsDelete(@PathVariable String id) {
        courseService.deleteCourse(id);

        return "redirect:/";
    }

    //-------------------------------
    //------------->ADD<-------------
    //-------------------------------
    //todo: fix edit page with js
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("levels", CourseLevelEnum.values());
        return "course-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid CourseAddBindingModel courseAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("serviceCourseAddBindingModel", courseAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.serviceCourseAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        courseService.addCourse(modelMapper.map(courseAddBindingModel, CourseServiceModel.class));

        return "redirect:/";
    }

    //--------------------------------
    //------------->EDIT<-------------
    //--------------------------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("courseEdit",
                modelMapper.map(courseService.findById(id), CourseEditViewModel.class))
                .addAttribute("levels", CourseLevelEnum.values());
        return "course-edit";
    }

    @PostMapping("/edit/{id}")
    public String editConfirm(@PathVariable String id) {
        return "redirect:/";
    }

    //--------------------------------
    //------->MODEL ATTRIBUTES<-------
    //--------------------------------
    @ModelAttribute
    public CourseAddBindingModel serviceCourseAddBindingModel() {
        return new CourseAddBindingModel();
    }

    @ModelAttribute
    public CourseEditBindingModel courseEditBindingModel() {
        return new CourseEditBindingModel();
    }
}
