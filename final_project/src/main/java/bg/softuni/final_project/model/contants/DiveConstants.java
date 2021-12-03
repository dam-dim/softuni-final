package bg.softuni.final_project.model.contants;

import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;

import java.util.List;

import static bg.softuni.final_project.model.entity.enums.DiveLevelEnum.*;
import static bg.softuni.final_project.model.entity.enums.DiveLevelEnum.TECH;

public class DiveConstants {
    public static final List<String> INIT_DIVE_TYPES = List.of(
            "Try Dive",
            "Reck Dive",
            "Night Dive",
            "Deep Dive"
    );

    public static final List<DiveLevelEnum> INIT_DIVE_LEVELS = List.of(
            BEGINNER,
            ADVANCED,
            PROFESSIONAL,
            TECH
    );

    public static final List<String> INIT_DIVE_DESCRIPTIONS = List.of(
            "Description for Try Dive",
            "Description for Reck Dive",
            "Description for Night Dive",
            "Description for Deep Dive"
    );
}
