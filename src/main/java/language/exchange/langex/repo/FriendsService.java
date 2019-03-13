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

}
