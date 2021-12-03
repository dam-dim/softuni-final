package bg.softuni.final_project.model.contants;

import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;

import java.util.List;

import static bg.softuni.final_project.model.entity.enums.CourseLevelEnum.*;

public class CourseConstants {
    public static final List<String> INIT_COURSE_NAMES = List.of(
            "Open Water Diver",
            "Advanced Open Water Diver",
            "Divemaster",
            "Instructor",
            "Trimix"
    );

    public static final List<CourseLevelEnum> INIT_COURSE_LEVELS = List.of(
            BEGINNER,
            ADVANCED,
            PROFESSIONAL,
            PROFESSIONAL,
            TECH
    );

    public static final List<String> INIT_COURSE_DESCRIPTIONS = List.of(
            "Description for Open Water Diver",
            "Description for Advanced Open Water Diver",
            "Description for Divemaster",
            "Description for Instructor",
            "Description for Trimix"
    );
}
