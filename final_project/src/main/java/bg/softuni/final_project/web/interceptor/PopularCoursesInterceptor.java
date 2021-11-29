package bg.softuni.final_project.web.interceptor;

import bg.softuni.final_project.service.CourseService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PopularCoursesInterceptor implements HandlerInterceptor {
    private final CourseService courseService;

    public PopularCoursesInterceptor(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        courseService.onRequest(request);
        return true;
    }
}
