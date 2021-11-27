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

import static bg.softuni.final_project.model.entity.enums.CourseLevelEnum.*;

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

        String name = String.join("-",courseServiceModel.getName().toLowerCase().split("\\s++"));
        course.setImageUrl("/images/services/courses/" + name + ".jpg");

        //todo: implement unique courses

        courseRepository.save(course);
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
        List<String> courseNames = List.of(
                "Open Water Diver",
                "Advanced Open Water Diver",
                "Divemaster",
                "Instructor",
                "Trimix"
        );

        List<CourseLevelEnum> courseLevels = List.of(
                BEGINNER,
                ADVANCED,
                PROFESSIONAL,
                PROFESSIONAL,
                TECH
        );

        List<String> courseDescription = List.of(
                "Open Water Diver",
                "Advanced Open Water Diver",
                "Divemaster",
                "Instructor",
                "Trimix"
        );

        List<String> courseImageUrl = List.of(
                "/images/services/courses/open-water-diver.jpg",
                "/images/services/courses/advanced-open-water-diver.jpg",
                "/images/services/courses/divemaster.jpg",
                "/images/services/courses/instructor.jpg",
                "/images/services/courses/trimix.jpg"
        );

        for (int i = 0; i < n; i++) {
            CourseEntity course = new CourseEntity();
            course
                    .setName(courseNames.get(i))
                    .setLevel(courseLevels.get(i))
                    .setDescription(courseDescription.get(i))
                    .setImageUrl(courseImageUrl.get(i));

            courseRepository.save(course);
        }
    }
}
