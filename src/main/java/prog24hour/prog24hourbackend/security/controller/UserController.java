package prog24hour.prog24hourbackend.security.controller;

import org.springframework.http.ResponseEntity;
import prog24hour.prog24hourbackend.security.dto.UserRequest;
import prog24hour.prog24hourbackend.security.dto.UserResponse;
import prog24hour.prog24hourbackend.security.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<Iterable<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id, @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

//    Take care with this. This should NOT be something everyone can call
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/add-role/{email}/{role}")
    public ResponseEntity<String> addRole(@PathVariable String email, @PathVariable String role) {
        userService.addRole(email, role);
        return ResponseEntity.ok("Role added");
    }

    //Take care with this. This should NOT be something everyone can call
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/remove-role/{email}/{role}")
//    @Operation(summary = "Removes a role from a User", description = "Caller must be authenticated with the role ADMIN")
    public ResponseEntity<String> removeRole(@PathVariable String email, @PathVariable String role) {
        userService.removeRole(email, role);
        return ResponseEntity.ok("Role removed");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }
}
