package language.exchange.langex;

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

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MockitoApplicationTests {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendsService friendService;

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



/*
    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect() {
        Mockito.when(productService.getProductName()).thenReturn("Mock Product Name");
        String testName = orderService.getProductName();
        Assert.assertEquals("Mock Product Name", testName);
    }
    */
}

