package bg.softuni.final_project.model.validator.course_name;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCourseNameValidator.class)
public @interface UniqueCourseName {
    String message() default "Course name is not unique";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
