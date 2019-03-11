package language.exchange.langex;


import language.exchange.langex.repo.UserService;
import language.exchange.langex.model.User;
import org.bouncycastle.math.raw.Mod;
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
        return "start";
    }

    @PostMapping("/")
    public String postIndex(@RequestParam("text") String text, Model model) {
        model.addAttribute("info", text);
        System.out.println(text);
        return "start";
    }

   @GetMapping("/profile")
     public String profile(Principal principal, Model model) {
       model.addAttribute("googleID", principal.getName());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(Principal principal, Model model,
                                @RequestParam("fname") String firstName,
                                @RequestParam("age") int age,
                                @RequestParam("lang") String language,
                                @RequestParam("llang") String lLangauge,
                                @RequestParam("intrests") String interests) {
        User user = new User(age, principal.getName(), firstName, "lastName", language, lLangauge, "unknown",
                interests, "default.PNG", "biography");

        userService.saveOrUpdate(user);
        return "profile";
    }

    @GetMapping("/signUp")
    public String signUp(Principal principal, Model model) {
        model.addAttribute("googleID", principal.getName());
        return "signUp";
    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        boolean userStatus = userService.checkUserStatus(principal.getName());
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {
            //model.addAttribute("userId", principal.getName());
            return "profile";
        }
    }

    @PostMapping("/signUp")
    public String initProfile(Principal principal, Model model,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("age") int age,
                              @RequestParam("cLvlLangs") String cLvlLangs,
                              @RequestParam("bLvlLangs") String bLvlLangs,
                              @RequestParam("aLvlLangs") String aLvlLangs,
                              @RequestParam("hobbies") String hobbies,
                              @RequestParam("bioGraphy") String bioGraphy) {
        User user = new User(age, principal.getName(), firstName, lastName, cLvlLangs, bLvlLangs, aLvlLangs,
                hobbies, "default.PNG", bioGraphy);
        System.out.println(user.toString());
        System.out.println("I am google ID:");
        System.out.println(principal.getName());
        userService.saveOrUpdate(user);

        return "redirect:/profile";
    }
}