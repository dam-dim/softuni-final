package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.enums.DiveLevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiveRepository extends JpaRepository<DiveEntity, String> {
    List<DiveEntity> findAllByLevel(DiveLevelEnum level);

    Optional<DiveEntity> findByTypeIgnoreCase(String type);
}
