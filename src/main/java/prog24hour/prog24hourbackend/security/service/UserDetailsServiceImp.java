package prog24hour.prog24hourbackend.security.service;

import saxxen.security.entity.User;
import saxxen.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  //To ensure same response is made for wrong username OR password
  public static final String WRONG_USERNAME_OR_PASSWORD = "Incorrect username or password";
  UserRepository userRepository;

    public UserDetailsServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    final Optional<User> optionalUser = userRepository.findByEmail(email);
    return optionalUser.orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,WRONG_USERNAME_OR_PASSWORD));
  }
}
