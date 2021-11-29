package bg.softuni.final_project.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    public ModelAndView handleNotFoundExceptions(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");

        LOGGER.warn("Message: {}", e.getMessage());

        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            LOGGER.error(stackTraceElement.toString());
        }

//        modelAndView
//                .addObject("message", e.getMessage())

        return modelAndView;
    }

}
