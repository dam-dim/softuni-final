package bg.softuni.final_project.init;

import bg.softuni.final_project.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final CourseService courseService;
    private final DiveService diveService;
    private final ReviewService reviewService;
    private final WeatherService weatherService;

    public DBInit(UserService userService, CourseService courseService, DiveService diveService, ReviewService reviewService, WeatherService weatherService) {
        this.userService = userService;
        this.courseService = courseService;
        this.diveService = diveService;
        this.reviewService = reviewService;
        this.weatherService = weatherService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initialiseUsersAndRoles();
        courseService.initialiseCourses();
        courseService.initialiseCourseClicks();
        diveService.initialiseDives();
        reviewService.initialiseReviews();
        weatherService.writeWeatherToJson();
    }
}
