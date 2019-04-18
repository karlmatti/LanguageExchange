package language.exchange.langex.controller;


import language.exchange.langex.model.User;
import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @Autowired
    UserService userService;

    @GetMapping("/friends/{id}")
    private Map<String, String> getFriendByID(@PathVariable("id") String id) {

        return friendsService.getUserFriendsAndChats(id);
    }

    @GetMapping("/friends/requests")
    public List<User> getUserFriends(Principal principal) {
        System.out.println("koik sobrad koos");
        List<String> friendRequests = friendsService.getFriendRequests(principal.getName());
        List<User> friendReqUsers = userService.getUsersById(friendRequests, principal.getName());
        System.out.println(friendReqUsers.toString());
        return friendReqUsers;
    }
}
