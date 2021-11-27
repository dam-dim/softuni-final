package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.CourseEntity;
import bg.softuni.final_project.model.entity.DiveEntity;
import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.UserServiceModel;
import bg.softuni.final_project.repository.UserRepository;
import bg.softuni.final_project.repository.UserRoleRepository;
import bg.softuni.final_project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FinalProjectUserServiceImpl finalProjectUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, FinalProjectUserServiceImpl finalProjectUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.finalProjectUserService = finalProjectUserService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        List<CourseEntity> courses = new ArrayList<>();
        List<DiveEntity> dives = new ArrayList<>();

        user
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setCourses(courses)
                .setDives(dives)
                .setRoles(Set.of(userRoleRepository.findByRole(UserRoleEnum.USER)));

        user = userRepository.save(user);

        UserDetails principal = finalProjectUserService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void initialiseUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity();
            adminRole.setRole(UserRoleEnum.ADMIN);

            UserRoleEntity userRole = new UserRoleEntity();
            userRole.setRole(UserRoleEnum.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Damyan")
                    .setLastName("Dimov")
                    .setEmail("ddimov99@mail.bg")
                    .setRoles(Set.of(adminRole, userRole));
            userRepository.save(admin);

            UserEntity user = new UserEntity();
            user
                    .setUsername("karo")
                    .setPassword(passwordEncoder.encode("karo"))
                    .setFirstName("Karo")
                    .setLastName("Badalyan")
                    .setEmail("karo@abv.bg")
                    .setRoles(Set.of(userRole));
            userRepository.save(user);
        }
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }
}

