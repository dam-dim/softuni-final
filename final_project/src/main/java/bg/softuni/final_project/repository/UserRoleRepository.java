package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
    UserRoleEntity findByRole(UserRoleEnum roleEnum);
}
