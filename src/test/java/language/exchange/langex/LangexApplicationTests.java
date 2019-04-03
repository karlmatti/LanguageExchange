package language.exchange.langex;

import language.exchange.langex.controller.MainController;
import language.exchange.langex.model.User;
import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class LangexApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private MainController mainController;

    @Autowired
    private FriendsService friendsService;

    @Test
    public void usersUpdatedInfoIsChanged() {
        User user = new User(20, "22222", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "default.PNG", "bioGraphy");
        User insertUser = userService.saveOrUpdate(user);

        user.setAge(21);
        User updateuser = userService.saveOrUpdate(user);

        Assert.assertNotEquals(insertUser.getAge(), updateuser.getAge());

    }

    @Test
    public void registeredUserIsFoundById() {
        User user = new User(20, "22222", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "hobbies", "default.PNG", "bioGraphy");
        User registeredUser = userService.saveOrUpdate(user);

        User userById = userService.getUserById(user.getId());

        Assert.assertEquals(registeredUser.getId(), userById.getId());
    }

    @Test
    public void searchReturnsCorrectUsers() {
        User user = new User(20, "22222", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "basketball", "default.PNG", "bioGraphy");
        User wrongUser = new User(20, "33333", "firstname",
                "lastName", "cLvlLangs", "bLvlLangs", "aLvlLangs",
                "dont have any", "default.PNG", "bioGraphy");

        userService.saveOrUpdate(user);
        userService.saveOrUpdate(wrongUser);
        List<User> allUsers = userService.getAllUsers();

        int searchByHobbies = 2;
        String hobby = "basketball";
        String searchingUserId = "55555";

        System.out.println("I am here> " + mainController.filterUsers(
                allUsers,
                hobby,
                searchByHobbies,
                searchingUserId));
        List<User> actualResult = mainController.filterUsers(
                allUsers,
                hobby,
                searchByHobbies,
                searchingUserId);
        List<User> correctResult = new ArrayList<>();
        correctResult.add(user);
        Assert.assertEquals(correctResult.size(), actualResult.size());
        Assert.assertEquals(correctResult.get(0).getId(), actualResult.get(0).getId());

    }

    @Test
    public void addingOtherAsFriendIsSuccessful() {
        String userId = "12345";
        String friendId = "67890";
        int counter = 1;
        boolean successful = friendsService.addFriends(userId, friendId, counter + ".txt");

        Assert.assertTrue(successful);

    }
}

