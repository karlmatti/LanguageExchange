package language.exchange.langex;


import language.exchange.langex.model.User;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    private User getUser(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PostMapping("/users")
    private String saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getGoogleId();
    }
/*
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        System.out.println("olen logoutis");
        return "/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
*/
    /*@PutMapping("/users/{id}")
    private String updateUser(@PathVariable int id, @RequestBody User user) {
        userService.update(id, user);
    }*/


}
