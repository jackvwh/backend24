package prog24hour.prog24hourbackend.security.service;

import jakarta.transaction.Transactional;
import saxxen.dtubar.entity.Resident;
import saxxen.dtubar.service.ResidentService;
import saxxen.security.dto.UserRequest;
import saxxen.security.dto.UserResponse;
import saxxen.security.entity.Role;
import saxxen.security.entity.User;
import saxxen.security.repository.RoleRepository;
import saxxen.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final ResidentService residentService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ResidentService residentService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.residentService = residentService;
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getResident().getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
        }
        try {
            // create or retrieve resident
            Resident resident = residentService.createOrGetResident(userRequest.getResident());
            String pwd = passwordEncoder.encode(userRequest.getPassword());

            User user = new User();
            user.setResident(resident);
            user.setPassword(pwd);
            user.setCreatedBy(resident.getEmail());

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

                residentService.updateResident(body.getResident());
                // should be set from security context
                user.setUpdatedBy(body.getResident().getEmail());

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
            residentService.deleteResident(user.getResident().getEmail());
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while deleting user: " + e.getMessage());
        }
    }
}
