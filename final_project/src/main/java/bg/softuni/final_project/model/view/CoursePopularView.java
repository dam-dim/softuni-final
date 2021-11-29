package bg.softuni.final_project.model.view;

import java.util.List;

public class CoursePopularView {
    private List<CourseViewModel> topCourses;

    public CoursePopularView() {
    }

    public List<CourseViewModel> getTopCourses() {
        return topCourses;
    }

    public CoursePopularView setTopCourses(List<CourseViewModel> topCourses) {
        this.topCourses = topCourses;
        return this;
    }
}
