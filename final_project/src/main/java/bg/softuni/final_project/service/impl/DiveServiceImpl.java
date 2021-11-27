package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import bg.softuni.final_project.model.service.DiveServiceModel;
import bg.softuni.final_project.repository.DiveRepository;
import bg.softuni.final_project.service.DiveService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.final_project.model.entity.enums.DiveLevelEnum.*;

@Service
public class DiveServiceImpl implements DiveService {
    private final DiveRepository diveRepository;
    private final ModelMapper modelMapper;

    public DiveServiceImpl(DiveRepository diveRepository, ModelMapper modelMapper) {
        this.diveRepository = diveRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addDive(DiveServiceModel diveServiceModel) {
        DiveEntity dive = modelMapper.map(diveServiceModel, DiveEntity.class);

        String name = String.join("-",diveServiceModel.getType().toLowerCase().split("\\s++"));
        dive.setImageUrl("/images/services/dives/" + name + ".jpg");

        //todo: implement unique dives

        diveRepository.save(dive);
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
        return modelMapper.map(diveRepository.findById(id).orElse(null), DiveServiceModel.class);
    }

    @Override
    public void initialiseDives() {
        int n = 4;
        List<String> diveTypes = List.of(
                "Try Dive",
                "Reck Dive",
                "Night Dive",
                "Deep Dive"
        );

        List<String> diveDescription = List.of(
                "Try Dive",
                "Reck Dive",
                "Night Dive",
                "Deep Dive"
        );

        List<String> diveImageUrl = List.of(
                "/images/services/dives/try-dive.jpg",
                "/images/services/dives/reck-dive.jpg",
                "/images/services/dives/night-dive.jpg",
                "/images/services/dives/deep-dive.jpg"
        );

        List<DiveLevelEnum> diveLevels = List.of(
                BEGINNER,
                ADVANCED,
                PROFESSIONAL,
                TECH
        );

        for (int i = 0; i < n; i++) {
            DiveEntity dive = new DiveEntity();
            dive
                    .setType(diveTypes.get(i))
                    .setLevel(diveLevels.get(i))
                    .setImageUrl(diveImageUrl.get(i))
                    .setDescription(diveDescription.get(i));
            diveRepository.save(dive);
        }
    }
}
