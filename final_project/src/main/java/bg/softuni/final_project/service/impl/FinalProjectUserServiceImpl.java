package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinalProjectUserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public FinalProjectUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found."));

        return mapToUserDetails(user);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        List<GrantedAuthority> grantedAuthorities =userEntity
                .getRoles()
                .stream()
                .map(userRoleEntity -> new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name()))
                .collect(Collectors.toList());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                grantedAuthorities);
    }
}
