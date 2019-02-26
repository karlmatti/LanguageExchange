package language.exchange.langex;

import language.exchange.langex.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("eventName", "FIFA 2018");
        System.out.println(model.toString());
        return "index";
    }

    @PostMapping("/")
    public String postIndex(@RequestParam("text") String text, Model model) {
        model.addAttribute("info", text);
        System.out.println(text);
        return "index";
    }


    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        model.addAttribute("userId", principal.getName());
        System.out.println(principal.getClass().getDeclaredMethods().length);

        return "user";
    }

    @RequestMapping(path = "/newUser")
    public String initProfile(Principal principal, Model model,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("cLvlLangs") String cLvlLangs,
                              @RequestParam("bLvlLangs") String bLvlLangs,
                              @RequestParam("aLvlLangs") String aLvlLangs,
                              @RequestParam("hobbies") String hobbies) {
        //after logging in first time, first data must be set
        //coming soon
        /*
        User user = new User(Long.parseLong(principal.getName()), firstName, lastName, cLvlLangs, bLvlLangs,
                aLvlLangs, hobbies, "photo");
        System.out.println(user.toString());*/
        /*User user = new User(Long.parseLong(principal.getName()), firstName, lastName, cLvlLangs, bLvlLangs,
                aLvlLangs, hobbies, "photo");*/
        //int googleId = Integer.parseInt(principal.getName());
        User user = new User(123, firstName, lastName, cLvlLangs, bLvlLangs,
                aLvlLangs, hobbies, "photo");
        System.out.println(user.toString());
        return "user";
    }

    @GetMapping("/profile")
    public String index() {

        return "profile";
    }

    @PostMapping("/profile")
    public String profile() {

        return "profile";
    }
}