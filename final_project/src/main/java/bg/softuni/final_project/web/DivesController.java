package bg.softuni.final_project.web;

import bg.softuni.final_project.model.binding.DiveAddBindingModel;
import bg.softuni.final_project.model.binding.DiveEditBindingModel;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.service.DiveServiceModel;
import bg.softuni.final_project.model.view.DiveDetailsViewModel;
import bg.softuni.final_project.model.view.DiveViewModel;
import bg.softuni.final_project.service.DiveService;
import bg.softuni.final_project.web.exception.DiveNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    //--------------------------------
    //------------->HOME<-------------
    // -------------------------------
    @GetMapping
    public String dives(Model model) {
        Map<String, List<DiveViewModel>> dives = new LinkedHashMap<>();

        for (DiveLevelEnum levelEnum : DiveLevelEnum.values()) {
            dives.put(levelEnum.name(),
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
    @Secured("ROLE_ADMIN")
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("levels", DiveLevelEnum.values());
        return "dive-add";
    }

    @Secured("ROLE_ADMIN")
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

        return "redirect:/services/dives";
    }

    //---------------------------------
    //------------DETAILS--------------
    //---------------------------------
    @GetMapping("/{id}/details")
    public String details(@PathVariable String id, Model model) {
        DiveDetailsViewModel dive = modelMapper.map(diveService.findById(id), DiveDetailsViewModel.class);

        if (dive == null) {
            throw new DiveNotFoundException("Dive with id " + id + " not found.");
        }

        model.addAttribute("dive", dive);

        return "dive-details";
    }

    //--------------------------------
    //------------>DELETE<------------
    //--------------------------------
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}/details")
    public String detailsDelete(@PathVariable String id) {
        diveService.deleteDive(id);
        return "redirect:/services/dives";
    }

    //--------------------------------
    //------------->EDIT<-------------
    //--------------------------------
    @Secured({"ROLE_ADMIN","ROLE_ADMINISTRATOR"})
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("diveModel",
                        modelMapper.map(diveService.findById(id), DiveEditBindingModel.class))
                .addAttribute("levels", DiveLevelEnum.values());

        diveService.setCurrentEditDiveType(diveService.findById(id).getType());
        return "dive-edit";
    }

    @GetMapping("/{id}/edit/errors")
    public String editOfferErrors(@PathVariable String id, Model model) {
        model.addAttribute("levels", DiveLevelEnum.values());
        return "dive-edit";
    }

    @Secured({"ROLE_ADMIN","ROLE_ADMINISTRATOR"})
    @PatchMapping("/{id}/edit")
    public String editConfirm(
            @PathVariable String id,
            @Valid DiveEditBindingModel diveModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("diveModel", diveModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.diveModel",
                            bindingResult);

            return "redirect:/services/dives/" + id + "/edit/errors";
        }

        diveService.editDive(modelMapper.map(diveModel, DiveServiceModel.class));

        return "redirect:/services/dives/"+id+"/details";
    }

    @ModelAttribute
    public DiveAddBindingModel diveAddBindingModel() {
        return new DiveAddBindingModel();
    }
}
