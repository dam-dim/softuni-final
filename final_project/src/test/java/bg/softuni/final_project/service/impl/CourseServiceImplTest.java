package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.model.service.CourseServiceModel;
import bg.softuni.final_project.repository.CourseRepository;
import bg.softuni.final_project.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;


class CourseServiceImplTest {
    private CourseEntity testCourse;
    private CourseRepository mockedCourseRepository;
    private ModelMapper modelMapper;

    private static final String COURSE_NAME = "Test Name";
    private static final CourseLevelEnum COURSE_LEVEL = CourseLevelEnum.TECH;
    private static final String COURSE_DESCRIPTION = "Test Description";
    private static final String COURSE_IMAGE_URL = "/images/services/courses/test-name.jpg";

    @BeforeEach
    public void init() {
        testCourse = new CourseEntity()
                .setName(COURSE_NAME)
                .setLevel(COURSE_LEVEL)
                .setDescription(COURSE_DESCRIPTION)
                .setImageUrl(COURSE_IMAGE_URL);

        mockedCourseRepository = Mockito.mock(CourseRepository.class);
    }

    @Test
    public void addCourse_ShouldAddCorrect() {
        //Arrange
        Mockito.when(mockedCourseRepository.save(testCourse)).thenReturn(testCourse);
        CourseService courseService = new CourseServiceImpl(mockedCourseRepository, modelMapper);
        //Act
        courseService.addCourse(modelMapper.map(testCourse, CourseServiceModel.class));
        //Assert

    }
}