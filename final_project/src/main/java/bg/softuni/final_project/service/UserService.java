package bg.softuni.final_project.service;

import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void initialiseUsersAndRoles();

    boolean isUserNameFree(String username);

    UserEntity findById(String id);

    UserEntity findUserByUsername(String username);

    List<UserServiceModel> findAllNotAdmin();

    void updateUserRole(String id, UserRoleEnum role);

    void deleteUser(String id);

    List<UserEntity> findAllUsersWithRole(UserRoleEnum roleEnum);

    boolean isAdmin(String name);
}
