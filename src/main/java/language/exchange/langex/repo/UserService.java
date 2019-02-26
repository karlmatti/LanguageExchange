package language.exchange.langex.repo;

import language.exchange.langex.model.User;
import language.exchange.langex.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    language.exchange.langex.repo.UserRepository UserRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        UserRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public User getUserById(int id) {
        return UserRepository.findById(id).get();
    }

    public void saveOrUpdate(User user) {
        UserRepository.save(user);
    }

    public void delete(int id) {
        UserRepository.deleteById(id);
    }
}
