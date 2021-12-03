package bg.softuni.final_project.repository;

import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @Query("SELECT u FROM UserEntity u WHERE :role NOT MEMBER OF u.roles")
    List<UserEntity> findAllByRoleNot(@Param("role") UserRoleEntity role);

    @Query("SELECT u FROM UserEntity u WHERE :role MEMBER OF u.roles")
    List<UserEntity> findAllByRole(@Param("role") UserRoleEntity role);
}
