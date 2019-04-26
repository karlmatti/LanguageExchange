package language.exchange.langex.repo;

import language.exchange.langex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository UserRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        UserRepository.findAll().forEach(users::add);
        return users;
    }

    public User getUserById(String id) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User saveOrUpdate(User user)
    {
        UserRepository.save(user);
        return getUserById(user.getId());
    }

    public void delete(int id) {
        UserRepository.deleteById(id);
    }


    public boolean checkUserStatus(String googleId) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(googleId)) {
                return false;
            }
        }
        return true;
    }


    public List<User> getUsersById(List<String> friendRequests, String principal) {
        List<User> usersList = new ArrayList<>();
        for (String userId : friendRequests) {
            for (User user : UserRepository.findAll()) {
                if (user.getId().equals(userId)) {
                    usersList.add(user);
                }
            }
        }
        return usersList;
    }
}
