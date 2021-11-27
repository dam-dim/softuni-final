package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiveRepository extends JpaRepository<DiveEntity, String> {
    List<DiveEntity> findAllByLevel(DiveLevelEnum level);
}
