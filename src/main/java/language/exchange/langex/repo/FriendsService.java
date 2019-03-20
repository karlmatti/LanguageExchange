package language.exchange.langex.repo;


import language.exchange.langex.model.Friends;
import language.exchange.langex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    @Autowired
    FriendsRepository FriendsRepository;

    public List<String> getUserFriends(String userId) {
        List<String> friends = new ArrayList<>();
        for (Friends friend : FriendsRepository.findAll()) {
            if (friend.getUserOne().equals(userId)) {
                friends.add(friend.getUserTwo());
            } else if (friend.getUserTwo().equals(userId)) {
                friends.add(friend.getUserOne());
            }
        }
        return friends;
    }

    public boolean addFriends(String userId, String friendId) {

        boolean addFriends = true;

        for (Friends friend : FriendsRepository.findAll()) {
            System.out.println("UserOne " + friend.getUserOne() + ";UserTwo " + friend.getUserTwo());
            System.out.println("UserId " + userId + ";FriendId " + friendId);
            System.out.println("1:" + (friend.getUserOne().equals(userId) && friend.getUserTwo().equals(friendId)));
            System.out.println("2:" + (friend.getUserOne().equals(friendId) && friend.getUserTwo().equals(userId)));
            if (friend.getUserOne().equals(userId) && friend.getUserTwo().equals(friendId)) {

                addFriends = false;
            } else if (friend.getUserOne().equals(friendId) && friend.getUserTwo().equals(userId)) {
                addFriends = false;
            }
        }
        if (addFriends) {
            System.out.println("Saving...");
            Friends friend = new Friends(userId, friendId);
            //error
            FriendsRepository.save(friend);
        }
        return addFriends;
    }

}
