package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, String> {
    void deleteAllByAuthorId(String id);
}
