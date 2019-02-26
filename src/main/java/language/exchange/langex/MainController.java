package language.exchange.langex;


import language.exchange.langex.repo.UserService;
import language.exchange.langex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class MainController {
    @Autowired
    UserService userService;

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

    @GetMapping("/profile")
    public String index() {

        return "profile";
    }

    @PostMapping("/profile")
    public String profile() {

        return "profile";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        boolean userStatus = userService.checkUserStatus(principal.getName());
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "user";
        } else {
            return "redirect:/profile/";

        }


    }

    @RequestMapping(path = "/newUser")
    public String initProfile(Principal principal,
                              Model model,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("cLvlLangs") String cLvlLangs,
                              @RequestParam("bLvlLangs") String bLvlLangs,
                              @RequestParam("aLvlLangs") String aLvlLangs,
                              @RequestParam("hobbies") String hobbies) {
        User user = new User(principal.getName(), firstName, lastName,
                cLvlLangs, bLvlLangs, aLvlLangs,
                hobbies, "photo");
        System.out.println(user.toString());
        userService.saveOrUpdate(user);
        return "redirect:/profile/";
    }
}