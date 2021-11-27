package bg.softuni.final_project.service;

import bg.softuni.final_project.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void initialiseUsersAndRoles();

    boolean isUserNameFree(String username);
}
