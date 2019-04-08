package language.exchange.langex.controller;


import language.exchange.langex.repo.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @GetMapping("/friends/{id}")
    private Map<String, String> getFriendByID(@PathVariable("id") String id) {
        return friendsService.getUserFriendsAndChats(id);
    }
}
