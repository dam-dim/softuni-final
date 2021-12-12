package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.repository.CourseRepository;
import bg.softuni.final_project.service.CourseService;
import bg.softuni.final_project.web.exception.CourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

import static bg.softuni.final_project.model.contants.CourseConstants.*;

@Service
public class CourseServiceImpl implements CourseService {
    private Map<String, Integer> courseCounter;
    private String currentEditCourseName = "";
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
        this.courseCounter = new HashMap<>();
    }

    @Override
    public void addCourse(CourseServiceModel courseServiceModel) {
        CourseEntity course = modelMapper.map(courseServiceModel, CourseEntity.class);
        course.setImageUrl(getImageUrl(courseServiceModel.getName()));

        courseRepository.save(course);
    }

    public String getImageUrl(String courseName) {
        String name = String.join("-",courseName.toLowerCase().split("\\s++"));

        return "/images/services/courses/" + name + ".jpg";
    }

    @Override
    public List<CourseServiceModel> findAllByLevel(CourseLevelEnum level) {
        return courseRepository
                .findAllByLevel(level)
                .stream()
                .map(courseEntity -> modelMapper.map(courseEntity, CourseServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseServiceModel findById(String id){
        return modelMapper.map(courseRepository
                .findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id '" + id + "' is not found.")),
                CourseServiceModel.class);
    }

    @Override
    public void deleteCourse(String id) {
        courseCounter.remove(id);
        courseRepository.deleteById(id);
    }

    @Override
    public void initialiseCourses() {
        int n = 5;

        for (int i = 0; i < n; i++) {
            String courseName = INIT_COURSE_NAMES.get(i);
            if (!isCourseNameFree(courseName)) continue;

            CourseEntity course = new CourseEntity();
            course
                    .setName(courseName)
                    .setLevel(INIT_COURSE_LEVELS.get(i))
                    .setDescription(INIT_COURSE_DESCRIPTIONS.get(i))
                    .setImageUrl(getImageUrl(courseName));

            courseRepository.save(course);
        }
    }

    @Override
    public boolean isCourseNameFree(String courseName) {
        return courseRepository.findByNameIgnoreCase(courseName).isEmpty();
    }

    @Override
    public boolean isCourseNameFreeEdit(String courseName) {
        return courseRepository.findAllByName(courseName).size() == 1 || courseRepository.findAllByName(courseName).size() == 0;
    }

    @Override
    public void editCourse(CourseServiceModel courseServiceModel, String id) {
        CourseEntity courseEntity = courseRepository
                .findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException("Course with id '" + id + "' not found."));

        courseEntity
                .setName(courseServiceModel.getName())
                .setLevel(courseServiceModel.getLevel())
                .setDescription(courseServiceModel.getDescription())
                .setImageUrl(courseServiceModel.getImageUrl());

        courseRepository.save(courseEntity);
    }

    @Override
    public void onRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String[] uriComponents = uri.split("/");

        if (uriComponents.length != 5) {
            return;
        }

        // with this 'if' I ensure that the uri is exactly /services/courses/{some id}/details
        if (uriComponents[1].equals("services") &&
                uriComponents[2].equals("courses") &&
                uriComponents[4].equals("details")) {

            String courseId = uriComponents[3];
            CourseEntity courseEntity = courseRepository
                    .findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id '"+courseId+"' not found."));

            courseCounter.putIfAbsent(courseId, 0);
            courseCounter.put(courseId, courseCounter.get(courseId)+1);
        }
    }

    @Override
    public List<CourseServiceModel> getSortedPopularCourses() {
        List<CourseServiceModel> popularCourses = new ArrayList<>();

        for (String courseId: courseCounter.keySet()) {
            CourseEntity course = courseRepository
                    .findById(courseId)
                    .orElseThrow(() -> new CourseNotFoundException("Course with id '" + courseId + "' not found."));

            CourseServiceModel courseServiceModel = modelMapper.map(course, CourseServiceModel.class);
            courseServiceModel.setClicks(courseCounter.get(courseId));

            popularCourses.add(courseServiceModel);
        }

        return popularCourses
                .stream()
                .sorted((course1, course2) -> course2.getClicks() - course1.getClicks())
                .collect(Collectors.toList());
    }

    @Override
    public void initialiseCourseClicks() {
        List<CourseEntity> all = courseRepository.findAll();

        for (CourseEntity course : all) {
            courseCounter.putIfAbsent(course.getId(), 0);
        }
    }

    @Override
    public List<CourseServiceModel> getTopNPopularCourses(int n) {
        if (n > courseCounter.size()) {
            n = courseCounter.size();
        }

        return getSortedPopularCourses()
                .stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public void setCurrentEditCourseName(String currentEditCourseName) {
        this.currentEditCourseName = currentEditCourseName;
    }

    public String getCurrentEditCourseName() {
        return currentEditCourseName;
    }

    @Override
    public boolean isNewCourseNameEqualToCurrent(String courseName) {
        return getCurrentEditCourseName().equals(courseName);
    }
}
