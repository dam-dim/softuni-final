package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.CourseAddBindingModel;
import bg.softuni.final_project.model.binding.CourseEditBindingModel;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.model.view.CourseDetailsViewModel;
import bg.softuni.final_project.model.view.CourseViewModel;
import bg.softuni.final_project.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
            courses.put(levelEnum.name(),
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
    @GetMapping("/{id}/details")
    public String details(@PathVariable String id, Model model) {

        model.addAttribute("course",
                modelMapper.map(courseService.findById(id), CourseDetailsViewModel.class));

        return "course-details";
    }

    //------------------------------
    //----------->DELETE<-----------
    //------------------------------
    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{id}/details")
    public String detailsDelete(@PathVariable String id) {
        courseService.deleteCourse(id);
        return "redirect:/services/courses";
    }

    //-------------------------------
    //------------->ADD<-------------
    //-------------------------------
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("levels", CourseLevelEnum.values());
        return "course-add";
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/add")
    public String addConfirm(@Valid CourseAddBindingModel courseAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("courseAddBindingModel", courseAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.courseAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        courseService.addCourse(modelMapper.map(courseAddBindingModel, CourseServiceModel.class));

        return "redirect:/services/courses";
    }

    //--------------------------------
    //------------->EDIT<-------------
    //--------------------------------
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("courseModel",
                        modelMapper.map(courseService.findById(id), CourseEditBindingModel.class))
                .addAttribute("levels", CourseLevelEnum.values());

        courseService.setCurrentEditCourseName(courseService.findById(id).getName());
        return "course-edit";
    }

    @GetMapping("/{id}/edit/errors")
    public String editOfferErrors(@PathVariable String id, Model model) {
        model.addAttribute("levels", CourseLevelEnum.values());
        return "course-edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_ADMINISTRATOR"})
    @PatchMapping("/{id}/edit")
    public String editConfirm(
            @PathVariable String id,
            @Valid CourseEditBindingModel courseModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("courseModel", courseModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.courseModel",
                            bindingResult);

            return "redirect:/services/courses/" + id +"/edit/errors";
        }

        courseService.editCourse(modelMapper.map(courseModel, CourseServiceModel.class), id);

        return "redirect:/services/courses/"+id+"/details";
    }

    //--------------------------------
    //------->MODEL ATTRIBUTES<-------
    //--------------------------------
    @ModelAttribute
    public CourseAddBindingModel serviceCourseAddBindingModel() {
        return new CourseAddBindingModel();
    }
}
