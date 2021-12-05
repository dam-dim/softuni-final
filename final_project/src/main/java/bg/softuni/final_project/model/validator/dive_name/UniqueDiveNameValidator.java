package bg.softuni.final_project.model.validator.dive_name;

import bg.softuni.final_project.service.DiveService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueDiveNameValidator implements ConstraintValidator<UniqueDiveType, String> {
    private final DiveService diveService;

    public UniqueDiveNameValidator(DiveService diveService) {
        this.diveService = diveService;
    }

    @Override
    public boolean isValid(String diveType, ConstraintValidatorContext context) {
        if (diveType == null || diveService.isNewDiveTypeEqualToCurrent(diveType)) {
            return true;
        }

        return diveService.isDiveTypeFree(diveType);
    }
}
