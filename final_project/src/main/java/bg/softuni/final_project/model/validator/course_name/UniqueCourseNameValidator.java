package bg.softuni.final_project.model.validator.course_name;

import bg.softuni.final_project.service.CourseService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCourseNameValidator implements ConstraintValidator<UniqueCourseName, String> {
    private final CourseService courseService;

    public UniqueCourseNameValidator(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean isValid(String courseName, ConstraintValidatorContext context) {
        if (courseName == null || courseService.isNewCourseNameEqualToCurrent(courseName)) {
            return true;
        }

        return courseService.isCourseNameFree(courseName);
    }
}
