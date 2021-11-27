package bg.softuni.final_project.init;

import bg.softuni.final_project.service.CourseService;
import bg.softuni.final_project.service.DiveService;
import bg.softuni.final_project.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {
    private final UserService userService;
    private final CourseService courseService;
    private final DiveService diveService;

    public DBInit(UserService userService, CourseService courseService, DiveService diveService) {
        this.userService = userService;
        this.courseService = courseService;
        this.diveService = diveService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initialiseUsersAndRoles();
        courseService.initialiseCourses();
        diveService.initialiseDives();
    }
}
