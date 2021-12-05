package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import bg.softuni.final_project.repository.CourseRepository;
import bg.softuni.final_project.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {
    private CourseEntity testCourse;

    @Mock
    private CourseRepository mockedCourseRepository;

    private ModelMapper modelMapper;

    private CourseService courseService;

    private static final String COURSE_NAME = "Test Name";
    private static final CourseLevelEnum COURSE_LEVEL = CourseLevelEnum.TECH;
    private static final String COURSE_DESCRIPTION = "Test Description";
    private static final String COURSE_IMAGE_URL = "/images/services/courses/test-name.jpg";

    @BeforeEach
    public void init() {
        courseService = new CourseServiceImpl(mockedCourseRepository, modelMapper);

        testCourse = new CourseEntity()
                .setName(COURSE_NAME)
                .setLevel(COURSE_LEVEL)
                .setDescription(COURSE_DESCRIPTION)
                .setImageUrl(COURSE_IMAGE_URL);
    }

    @Test
    public void testIsCourseNameFreeShouldReturnFalse() {
        Assertions.assertFalse(courseService.isCourseNameFree(COURSE_NAME));
    }

    @Test
    public void testIsCourseNameFreeShouldWork() {
        Assertions.assertTrue(courseService.isCourseNameFree("sadsad"));
    }


}