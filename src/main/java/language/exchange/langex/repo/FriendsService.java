package language.exchange.langex.repo;


import language.exchange.langex.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FriendsService {
    @Autowired
    FriendsRepository FriendsRepository;

    public Map<String, String>  getUserFriendsAndChats(String userId) {
        Map<String, String> friends = new HashMap<>();
        for (Friends friend : FriendsRepository.findAll()) {
            if (friend.getUserOne().equals(userId)) {
                friends.put(friend.getUserTwo(), friend.getChatNumber());
            } else if (friend.getUserTwo().equals(userId)) {
                friends.put(friend.getUserOne(), friend.getChatNumber());
            }
        }
        return friends;
    }

    public boolean addFriends(String userId, String friendId, String chatNumber) {

        boolean addFriends = true;
        for (Friends friend : FriendsRepository.findAll()) {
            if (friend.getUserOne().equals(userId) && friend.getUserTwo().equals(friendId)) {

                addFriends = false;
            } else if (friend.getUserOne().equals(friendId) && friend.getUserTwo().equals(userId)) {
                addFriends = false;
            }
        }
        if (addFriends) {
            Friends friend = new Friends(userId, friendId, chatNumber);

            FriendsRepository.save(friend);
        }
        return addFriends;
    }

}
