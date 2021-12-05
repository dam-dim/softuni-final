package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.enums.CourseLevelEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String> {
    List<CourseEntity> findAllByLevel(CourseLevelEnum level);

    CourseEntity getById(String id);

    Optional<CourseEntity> findByNameIgnoreCase(String name);

    List<CourseEntity> findAllByName(String name);
}
