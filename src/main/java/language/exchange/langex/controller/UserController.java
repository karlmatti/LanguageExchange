package language.exchange.langex.controller;


import language.exchange.langex.model.User;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{id}")
    private User getUser(@PathVariable("id") String id) {
        System.out.println("getUser");
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    private List<User> getAllUsers() {
        System.out.println("getUsersssssssss");

        return userService.getAllUsers();
    }
    /*
    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }*/

    /*
    @PostMapping("/users")
    private String saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getId();
    }
    */


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
