package language.exchange.langex;


import language.exchange.langex.model.Friends;
import language.exchange.langex.model.User;
import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @GetMapping("/friends/{id}")
    private List<String> getFriendByID(@PathVariable("id") String id) {
        return friendsService.getUserFriends(id);
    }
}
