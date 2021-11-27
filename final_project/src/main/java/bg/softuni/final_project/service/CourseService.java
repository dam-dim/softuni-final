package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;

import java.util.List;

public interface CourseService {
    void addCourse(CourseServiceModel courseServiceModel);

    List<CourseServiceModel> findAllByLevel(CourseLevelEnum level);

    CourseServiceModel findById(String id);

    void deleteCourse(String id);

    void initialiseCourses();
}
