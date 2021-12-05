package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.service.impl.CourseServiceImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CourseService {
    void addCourse(CourseServiceModel courseServiceModel);

    List<CourseServiceModel> findAllByLevel(CourseLevelEnum level);

    CourseServiceModel findById(String id);

    void deleteCourse(String id);

    void initialiseCourses();

    boolean isCourseNameFree(String courseName);

    boolean isCourseNameFreeEdit(String courseName);

    void editCourse(CourseServiceModel courseServiceModel, String id);

    void onRequest(HttpServletRequest request);

    List<CourseServiceModel> getTopNPopularCourses(int n);

    List<CourseServiceModel> getSortedPopularCourses();

    void initialiseCourseClicks();

    boolean isNewCourseNameEqualToCurrent(String courseName);

    void setCurrentEditCourseName(String currentEditCourseName);
}
