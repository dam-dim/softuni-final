package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.service.DiveServiceModel;
import bg.softuni.final_project.repository.DiveRepository;
import bg.softuni.final_project.service.DiveService;
import bg.softuni.final_project.web.exception.DiveNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.final_project.model.contants.DiveConstants.*;

@Service
public class DiveServiceImpl implements DiveService {
    private String currentEditDiveType = "";

    private final DiveRepository diveRepository;
    private final ModelMapper modelMapper;

    public DiveServiceImpl(DiveRepository diveRepository, ModelMapper modelMapper) {
        this.diveRepository = diveRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDive(DiveServiceModel diveServiceModel) {
        DiveEntity dive = modelMapper.map(diveServiceModel, DiveEntity.class);
        dive.setImageUrl(getImageUrl(diveServiceModel.getType()));

        diveRepository.save(dive);
    }

    public String getImageUrl(String diveType) {
        String name = String.join("-",diveType.toLowerCase().split("\\s++"));
        return "/images/services/dives/" + name + ".jpg";
    }

    @Override
    public List<DiveServiceModel> findAllByLevel(DiveLevelEnum levelEnum) {
        return diveRepository
                .findAllByLevel(levelEnum)
                .stream()
                .map(diveEntity -> modelMapper.map(diveEntity, DiveServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDive(String id) {
        diveRepository.deleteById(id);
    }

    @Override
    public DiveServiceModel findById(String id) {
        return modelMapper.map(diveRepository
                .findById(id)
                .orElseThrow(() -> new DiveNotFoundException("Dive with id '"+ id + "' not found.")),
                DiveServiceModel.class);
    }

    @Override
    public void initialiseDives() {
        int n = 4;

        for (int i = 0; i < n; i++) {
            String diveType = INIT_DIVE_TYPES.get(i);
            if (!isDiveTypeFree(diveType)) continue;

            DiveEntity dive = new DiveEntity();
            dive
                    .setType(diveType)
                    .setLevel(INIT_DIVE_LEVELS.get(i))
                    .setImageUrl(getImageUrl(diveType))
                    .setDescription(INIT_DIVE_DESCRIPTIONS.get(i));

            diveRepository.save(dive);
        }
    }

    @Override
    public boolean isDiveTypeFree(String diveType) {
        return diveRepository.findByTypeIgnoreCase(diveType).isEmpty();
    }

    @Override
    public void editDive(DiveServiceModel diveServiceModel) {
        DiveEntity diveEntity = diveRepository
                .findByTypeIgnoreCase(diveServiceModel.getType())
                .orElseThrow(() ->
                        new DiveNotFoundException("Dive with type '"+ diveServiceModel.getType() + "' not found."));

        diveEntity
                .setType(diveServiceModel.getType())
                .setLevel(diveServiceModel.getLevel())
                .setDescription(diveServiceModel.getDescription())
                .setImageUrl(diveServiceModel.getImageUrl());

        diveRepository.save(diveEntity);
    }

    @Override
    public boolean isNewDiveTypeEqualToCurrent(String diveType) {
        return getCurrentEditDiveType().equals(diveType);
    }

    @Override
    public void setCurrentEditDiveType(String type) {
        this.currentEditDiveType = type;
    }

    public String getCurrentEditDiveType() {
        return currentEditDiveType;
    }
}
