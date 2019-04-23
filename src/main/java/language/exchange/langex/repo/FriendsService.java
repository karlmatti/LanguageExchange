package language.exchange.langex.repo;


import language.exchange.langex.model.Friends;
import language.exchange.langex.model.User;
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
    @Autowired
    UserService UserService;

    public List<User> getUserFriendsById(String userId) {
        System.out.println("userId" + userId);
        List<User> requests = new ArrayList<>();
        for (Friends friend : FriendsRepository.findAll()) {
            System.out.println("!friend.getFriends()" + !friend.getFriends());
            if (friend.getUserTwo().equals(userId) && !friend.getFriends()) {
                User user = UserService.getUserById(friend.getUserOne());
                requests.add(user);
            }
        }
        return requests;

    }

    private List<Friends> getAllFriends() {
        List<Friends> friends = new ArrayList<>();
        FriendsRepository.findAll().forEach(friends::add);
        return friends;
    }

    public Map<String, String>  getUserFriendsAndChats(String userId) {
        Map<String, String> friends = new HashMap<>();
        for (Friends friend : FriendsRepository.findAll()) {
            if (friend.getUserOne().equals(userId) && friend.getFriends()) {
                friends.put(friend.getUserTwo(), friend.getChatNumber());
            } else if (friend.getUserTwo().equals(userId) && friend.getFriends()) {
                friends.put(friend.getUserOne(), friend.getChatNumber());
            }
        }
        return friends;
    }

    public boolean addFriends(String userId, String friendId, String chatNumber) {

        boolean addFriends = true;
        boolean friendsYet = false;
        for (Friends friend : FriendsRepository.findAll()) {
            if (friend.getUserOne().equals(userId) && friend.getUserTwo().equals(friendId)) {
                if (!friend.getFriends()) {
                    friendsYet = true;
                    FriendsRepository.delete(friend);
                }
                addFriends = false;
            } else if (friend.getUserOne().equals(friendId) && friend.getUserTwo().equals(userId)) {
                if (!friend.getFriends()) {
                    friendsYet = true;
                    FriendsRepository.delete(friend);
                }
                addFriends = false;
            }
        }
        if (friendsYet) {

            Friends friend = new Friends(userId, friendId, friendsYet, chatNumber);
            FriendsRepository.save(friend);
        } else if (addFriends) {

            Friends friend = new Friends(friendId, userId, friendsYet, chatNumber);
            FriendsRepository.save(friend);
        }
        return addFriends;
    }

    public List<String> getFriendRequests(String principal) {

        List<Friends> allFriends = getAllFriends();
        List<String> myFriendRequests = new ArrayList<>();

        for (Friends friends : allFriends) {
            if (!friends.getFriends() && friends.getUserOne().equals(principal)) {
                myFriendRequests.add(friends.getUserTwo());
            }
        }

        return myFriendRequests;
    }

    public void deleteFriendRequest(String userId, String friendId) {

        for (Friends friends : FriendsRepository.findAll()) {
            if (friends.getUserOne().equals(userId) && friends.getUserTwo().equals(friendId)) {
                FriendsRepository.delete(friends);
            } else if (friends.getUserTwo().equals(userId) && friends.getUserOne().equals(friendId)) {
                FriendsRepository.delete(friends);
            }
        }

    }
}
