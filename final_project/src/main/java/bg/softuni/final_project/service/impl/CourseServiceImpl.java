package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.repository.CourseRepository;
import bg.softuni.final_project.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.final_project.model.contants.CourseConstants.*;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCourse(CourseServiceModel courseServiceModel) {
        CourseEntity course = modelMapper.map(courseServiceModel, CourseEntity.class);
        course.setImageUrl(getImageUrl(courseServiceModel.getName()));

        //todo: implement unique courses

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
        return modelMapper.map(courseRepository.findById(id).orElse(null), CourseServiceModel.class);
    }

    @Override
    public void deleteCourse(String id) {
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

    public boolean isCourseNameFree(String courseName) {
        return courseRepository.findByNameIgnoreCase(courseName).isEmpty();
    }
}
