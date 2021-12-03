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
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.security.RolesAllowed;
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
    @GetMapping("/{id}/details")
    public String details(@PathVariable String id, Model model) {

        model.addAttribute("course",
                modelMapper.map(courseService.findById(id), CourseDetailsViewModel.class));

        return "course-details";
    }

    //------------------------------
    //----------->DELETE<-----------
    //------------------------------
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}/details")
    public String detailsDelete(@PathVariable String id) {
        courseService.deleteCourse(id);
        return "redirect:/services/courses";
    }

    //-------------------------------
    //------------->ADD<-------------
    //-------------------------------
    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("levels", CourseLevelEnum.values());
        return "course-add";
    }

    @Secured("ROLE_ADMIN")
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
    //todo: fix edit page with js
    @Secured({"ROLE_ADMIN", "ROLE_ADMINISTRATOR"})
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("courseEdit",
                modelMapper.map(courseService.findById(id), CourseEditViewModel.class))
                .addAttribute("levels", CourseLevelEnum.values());
        return "course-edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_ADMINISTRATOR"})
    @PatchMapping("/{id}/edit")
    public String editConfirm(@Valid CourseEditBindingModel courseEditBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, @PathVariable String id) {

        // todo: what if the new name/type/ is the same as the previous name/type/?

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("courseEditBindingModel", courseEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.courseEditBindingModel",
                            bindingResult);

            return "redirect:/services/courses/" + id +"/edit";
        }

        courseService.editCourse(modelMapper.map(courseEditBindingModel, CourseServiceModel.class), id);

        return "redirect:/services/courses";
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
