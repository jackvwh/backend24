package prog24hour.prog24hourbackend.security.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import saxxen.dtubar.entity.Resident;
import saxxen.dtubar.repository.ResidentRepository;
import saxxen.security.entity.Role;
import saxxen.security.entity.User;
import saxxen.security.repository.RoleRepository;
import saxxen.security.repository.UserRepository;

import java.util.NoSuchElementException;

@Component
public class SetupDevUsers implements ApplicationRunner {

    private final ResidentRepository residentRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public SetupDevUsers(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ResidentRepository residentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.residentRepository = residentRepository;
    }

    public void run(ApplicationArguments args) {
        setupUsers();
    }

    /***********************************************************************************************************************************''
     IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
     iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
     If you see the lines below in log-outputs on Azure, forget whatever had your attention on, AND FIX THIS PROBLEM
     *************************************************************************************************************************************/
    private void setupUsers() {
        Role roleAdmin = roleRepository.findById("ADMIN").orElseThrow(() -> new NoSuchElementException("Role 'admin' not found"));
        System.out.println("******************************************************************************");
        System.out.println("********** IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ************");
        System.out.println();
        System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
        System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
        System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
        System.out.println();
        System.out.println("******************************************************************************");

        // Create dummy resident
        Resident adminResident = new Resident();
        adminResident.setEmail("john.doe@example.com");
        adminResident.setFirstName("John");
        adminResident.setLastName("Doe");
        adminResident.setPhone("12345678");
        adminResident.setRoomId("1111");

        // Create admin user
        User admin = new User();
        admin.setResident(residentRepository.save(adminResident));
        admin.setPassword(passwordEncoder.encode("test12"));
        admin.addRole(roleAdmin);
        userRepository.save(admin);
    }
}
