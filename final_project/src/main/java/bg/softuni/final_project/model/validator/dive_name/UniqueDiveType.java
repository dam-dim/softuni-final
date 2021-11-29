package bg.softuni.final_project.model.validator.dive_name;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDiveNameValidator.class)
public @interface UniqueDiveType {
    String message() default "Dive type is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
