package prog24hour.prog24hourbackend.security.service;

import jakarta.transaction.Transactional;
import prog24hour.prog24hourbackend.security.dto.UserRequest;
import prog24hour.prog24hourbackend.security.dto.UserResponse;
import prog24hour.prog24hourbackend.security.entity.Role;
import prog24hour.prog24hourbackend.security.entity.User;
import prog24hour.prog24hourbackend.security.repository.RoleRepository;
import prog24hour.prog24hourbackend.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
//        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
//        }
        try {
            // create or retrieve resident

            User user = new User();

            // set default role
            Role role = roleRepository.findById("USER").orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
            user.addRole(role);
            role.addUser(user);

            return new UserResponse(userRepository.save(user));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while saving user: " + e.getMessage());
        }
    }

    @Transactional
    //Only way to change roles is via the addRole/removeRole methods
    public UserResponse updateUser(Integer id, UserRequest body) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            try {
                return new UserResponse(userRepository.save(user));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while updating user: " + e.getMessage());
            }
    }

    public UserResponse getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserResponse(user);
    }

    public  UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserResponse(user);
    }

    public Iterable<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::new).toList();
    }

    public void addRole(String email, String newRole) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Role role = roleRepository.findById(newRole).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        user.addRole(role);
        new UserResponse(userRepository.save(user));
    }

    public void removeRole(String residentEmail, String newRole) {
        User user = userRepository.findByEmail(residentEmail).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Role role = roleRepository.findById(newRole).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
        user.removeRole(role);
        new UserResponse(userRepository.save(user));
    }

    public void deleteUser(Integer id) {
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
            userRepository.delete(user);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while deleting user: " + e.getMessage());
        }
    }
}
