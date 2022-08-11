package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import recipes.entity.User;
import recipes.service.UserService;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;

    public UserController(@Autowired UserService userService, @Autowired PasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exist");
        } else {
            userService.saveUser(new User(user.getEmail(), encoder.encode(user.getPassword())));
        }
    }
}
