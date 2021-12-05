package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.service.DiveServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DiveService {
    void addDive(DiveServiceModel diveServiceModel);

    List<DiveServiceModel> findAllByLevel(DiveLevelEnum levelEnum);

    void deleteDive(String id);

    DiveServiceModel findById(String id);

    void initialiseDives();

    boolean isDiveTypeFree(String diveType);

    void editDive(DiveServiceModel diveServiceModel);

    boolean isNewDiveTypeEqualToCurrent(String diveType);

    void setCurrentEditDiveType(String diveType);
}
