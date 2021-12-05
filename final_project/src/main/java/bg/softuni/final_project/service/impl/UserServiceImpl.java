package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.model.entity.UserRoleEntity;
import bg.softuni.final_project.model.entity.enums.UserRoleEnum;
import bg.softuni.final_project.model.service.UserServiceModel;
import bg.softuni.final_project.repository.ReviewRepository;
import bg.softuni.final_project.repository.UserRepository;
import bg.softuni.final_project.repository.UserRoleRepository;
import bg.softuni.final_project.service.UserService;
import bg.softuni.final_project.web.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder passwordEncoder;
    private final FinalProjectUserServiceImpl finalProjectUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, UserRoleRepository userRoleRepository, ReviewRepository reviewRepository, PasswordEncoder passwordEncoder, FinalProjectUserServiceImpl finalProjectUserService) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.finalProjectUserService = finalProjectUserService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);

        user
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
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

            UserRoleEntity administratorRole = new UserRoleEntity();
            administratorRole.setRole(UserRoleEnum.ADMINISTRATOR);

            userRoleRepository.saveAll(List.of(adminRole, userRole, administratorRole));
        }
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
            UserRoleEntity administratorRole = userRoleRepository.findByRole(UserRoleEnum.ADMINISTRATOR);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Damyan")
                    .setLastName("Dimov")
                    .setEmail("ddimov99@mail.bg")
                    .setRoles(Set.of(adminRole, administratorRole));
            userRepository.save(admin);

            UserEntity administrator = new UserEntity();
            administrator
                    .setUsername("administrator")
                    .setPassword(passwordEncoder.encode("test"))
                    .setFirstName("Tatyana")
                    .setLastName("Dimova")
                    .setEmail("tanyadim@mail.bg")
                    .setRoles(Set.of(administratorRole));
            userRepository.save(administrator);

            UserEntity user = new UserEntity();
            user
                    .setUsername("karo")
                    .setPassword(passwordEncoder.encode("karo"))
                    .setFirstName("Karo")
                    .setLastName("Badalyan")
                    .setEmail("karo@abv.bg")
                    .setRoles(Set.of(userRole));
            userRepository.save(user);

            UserEntity user2 = new UserEntity();
            user2
                    .setUsername("ico")
                    .setPassword(passwordEncoder.encode("ico"))
                    .setFirstName("Hristo")
                    .setLastName("Dimov")
                    .setEmail("icodim@abv.bg")
                    .setRoles(Set.of(userRole));
            userRepository.save(user2);
        }
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id '" + id + "'  not found"));
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username '"+username+"' not found."));
    }

    @Override
    public List<UserServiceModel> findAllNotAdmin() {
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

        return mapListToService(userRepository.findAllByRoleNot(adminRole));
    }

    @Override
    public void updateUserRole(String id, UserRoleEnum role) {
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("user with id '" + id + "' not found."));

        UserRoleEntity roleEntity = userRoleRepository.findByRole(role);

        userEntity.setRoles(Set.of(roleEntity));

        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(String id) {
        reviewRepository.deleteAllByAuthorId(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<UserEntity> findAllUsersWithRole(UserRoleEnum roleEnum) {
        UserRoleEntity userRole = userRoleRepository.findByRole(roleEnum);

        return userRepository.findAllByRole(userRole);
    }

    @Override
    public boolean isAdmin(String name) {
        UserRoleEntity adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
        return findUserByUsername(name).getRoles().contains(adminRole);
    }

    // list from entity -> service
    private List<UserServiceModel> mapListToService(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(this::mapEntityToService)
                .collect(Collectors.toList());
    }

    // from entity -> service
    private UserServiceModel mapEntityToService(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserServiceModel.class);
    }
}

