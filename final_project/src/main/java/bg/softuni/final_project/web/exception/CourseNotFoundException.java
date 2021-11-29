package bg.softuni.final_project.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Provider;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends ServiceNotFoundException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
