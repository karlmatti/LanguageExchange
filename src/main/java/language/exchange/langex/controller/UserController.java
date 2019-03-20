package language.exchange.langex;


import language.exchange.langex.model.User;

import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    FriendsService friendsService;

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

    /*
    @PostMapping("/users")
    private String saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getId();
    }
    */
    @PostMapping("/addFriends")
    public String addFriends(@RequestParam("userId") String userId,
                             @RequestParam("friendId") String friendId) {
        boolean successful = friendsService.addFriends(userId, friendId);
        if (successful) {
            System.out.println(userId + " and " + friendId + " are now friends!");
        } else {
            System.out.println("Sorry but they are already friends!");
        }
        //returns friends profile later
        return "search";
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
