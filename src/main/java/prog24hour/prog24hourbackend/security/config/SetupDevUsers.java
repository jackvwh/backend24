package prog24hour.prog24hourbackend.security.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import prog24hour.prog24hourbackend.security.entity.Role;
import prog24hour.prog24hourbackend.security.entity.User;
import prog24hour.prog24hourbackend.security.repository.RoleRepository;
import prog24hour.prog24hourbackend.security.repository.UserRepository;
import java.util.NoSuchElementException;

@Component
public class SetupDevUsers implements ApplicationRunner {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public SetupDevUsers(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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

        // Create admin user
        User admin = new User();
        admin.setPassword(passwordEncoder.encode("test12"));
        admin.setEmail("john.doe@example.com");
        admin.addRole(roleAdmin);
        userRepository.save(admin);
    }
}
