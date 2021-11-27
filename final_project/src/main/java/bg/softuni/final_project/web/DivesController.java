package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.DiveAddBindingModel;
import bg.softuni.final_project.model.binding.DiveEditBindingModel;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.service.DiveServiceModel;
import bg.softuni.final_project.model.view.*;
import bg.softuni.final_project.service.DiveService;
import bg.softuni.final_project.web.exception.ServiceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/services/dives")
public class DivesController {
    private final DiveService diveService;
    private final ModelMapper modelMapper;

    public DivesController(DiveService diveService, ModelMapper modelMapper) {
        this.diveService = diveService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String dives(Model model) {
        Map<String, List<DiveViewModel>> dives = new LinkedHashMap<>();

        for (DiveLevelEnum levelEnum : DiveLevelEnum.values()) {
            dives.put(levelEnum.name().toLowerCase(),
                    diveService
                            .findAllByLevel(levelEnum)
                            .stream()
                            .map(diveServiceModel -> modelMapper.map(diveServiceModel, DiveViewModel.class))
                            .collect(Collectors.toList()));
        }

        model.addAttribute("dives", dives);
        return "dives";
    }

    //---------------------------------
    //--------------ADD----------------
    //---------------------------------
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("levels", DiveLevelEnum.values());
        return "dive-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid DiveAddBindingModel diveAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("diveAddBindingModel", diveAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.diveAddBindingModel",
                            bindingResult);

            return "redirect:add";
        }

        diveService.addDive(modelMapper.map(diveAddBindingModel, DiveServiceModel.class));

        return "redirect:/";
    }

    //---------------------------------
    //------------DETAILS--------------
    //---------------------------------
    //todo: fix the design for details page
    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        DiveDetailsViewModel dive = modelMapper.map(diveService.findById(id), DiveDetailsViewModel.class);

        if (dive == null) {
            throw new ServiceNotFoundException();
        }

        model.addAttribute("dive", dive);

        return "dive-details";
    }

    @PostMapping("/details/{id}")
    public String detailsDelete(@PathVariable String id) {
        diveService.deleteDive(id);
        return "redirect:/";
    }

    //--------------------------------
    //------------->EDIT<-------------
    //--------------------------------
    //todo: fix edit page with js
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("diveEdit",
                        modelMapper.map(diveService.findById(id), DiveEditViewModel.class))
                .addAttribute("levels", DiveLevelEnum.values());
        return "dive-edit";
    }

    @PostMapping("/edit/{id}")
    public String editConfirm(@PathVariable String id) {
        return "redirect:/";
    }

    @ExceptionHandler(ServiceNotFoundException.class)
    public ModelAndView handleDbExceptions(ServiceNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @ModelAttribute
    public DiveAddBindingModel diveAddBindingModel() {
        return new DiveAddBindingModel();
    }

    @ModelAttribute
    public DiveEditBindingModel diveEditBindingModel() {
        return new DiveEditBindingModel();
    }
}
