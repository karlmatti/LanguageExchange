package language.exchange.langex.controller;


import language.exchange.langex.model.User;
import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @GetMapping("/friends/requests/{userID}")
    public List<User> getUserFriendsByUserId(@PathVariable("userID") String id) {
        System.out.println("useriddddd onn" + id);
        List<User> friendRequests = new ArrayList<>();
        /*for (Friends friend : friendsService.getAllFriends()) {
            if (!friend.getFriends()) {
                friendRequests.add(userService.getUserById(friend.getUserOne()));
            }
        }*/
        friendRequests.add(new User(1, "231111123123123", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "default.PNG", "bioGraphy"));
        friendRequests.add(new User(3, "331111123123123", "3firstname",
                "3lastName", "3cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "3default.PNG", "bioGraphy"));
        return friendRequests;
    }


    @GetMapping("/friends/requests")
    public List<User> getUserFriends() {
        System.out.println("koik sobrad koos");
        List<User> friendRequests = new ArrayList<>();
        /*for (Friends friend : friendsService.getAllFriends()) {
            if (!friend.getFriends()) {
                friendRequests.add(userService.getUserById(friend.getUserOne()));
            }
        }*/
        friendRequests.add(new User(1, "231111123123123", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "default.PNG", "bioGraphy"));
        friendRequests.add(new User(3, "331111123123123", "3firstname",
                "3lastName", "3cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "3default.PNG", "bioGraphy"));
        return friendRequests;
    }
}
