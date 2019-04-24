package language.exchange.langex.controller;


import language.exchange.langex.model.User;
import language.exchange.langex.repo.FriendsService;
import language.exchange.langex.repo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    private static int counter = 5;
    @Autowired
    UserService userService;
    @Autowired
    FriendsService friendsService;


    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        model.addAttribute("googleID", principal.getName());
        model.addAttribute("userId", principal.getName());
        boolean userStatus = checkUserExistance(principal);

        if (userStatus) {

            return "redirect:/signUp";
        } else {

            return "profile";
        }
    }

    @GetMapping("/otherProfile")
    public String otherProfile(@RequestParam("userId") String userId,
                               Model model, Principal principal) {
        model.addAttribute("googleID", userId);
        boolean userStatus = checkUserExistance(principal);
        model.addAttribute("userId", principal.getName());
        if (userStatus) {

            return "redirect:/signUp";
        } else {
            return "otherProfile";
        }

    }


    @PostMapping("/profile")
    public String updateProfile(Principal principal, Model model,
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

        userService.saveOrUpdate(user);
        model.addAttribute("googleID", principal.getName());
        return "profile";
    }

    @GetMapping("/signUp")
    public String signUp(Principal principal, Model model) {
        boolean userStatus = checkUserExistance(principal);
        model.addAttribute("googleID", principal.getName());
        if (userStatus) {
            return "signUp";
        } else {
            return "redirect:/profile";
        }

    }

    @GetMapping("/user")
    public String user(Principal principal, Model model) {
        boolean userStatus = checkUserExistance(principal);
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {

            return "redirect:/profile";
        }
    }

    private boolean checkUserExistance(Principal principal) {
        return userService.checkUserStatus(principal.getName());
    }


    @PostMapping("/signUp")
    public String initProfile(Principal principal,
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
        userService.saveOrUpdate(user);

        return "redirect:/profile";
    }

    @GetMapping("/client/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @GetMapping("/search")
    public String search(Principal principal, Model model) {
        boolean userStatus = checkUserExistance(principal);
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {
            return "search";
        }

    }

    @PostMapping("/profile/add")
    public String addFriends(@RequestParam("friendId") String friendId,
                             Principal principal,
                             Model model) {
        boolean userStatus = checkUserExistance(principal);
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {

            boolean successful = friendsService.addFriends(principal.getName(), friendId, counter + ".txt");

            if (successful) {
                System.out.println(principal.getName() + " and " + friendId + " are now friends!");
                counter++;
            } else {
                System.out.println("Sorry but they are already friends!");
            }
            return "redirect:/profile";
        }
    }

    @PostMapping("/profile/delete")
    private String deleteRequest(Principal principal, @RequestParam("friendId") String friendId) {

        friendsService.deleteFriendRequest(principal.getName(), friendId);

        return "redirect:/profile";
    }
    @GetMapping("/searchResults")
    private String searchUsers(@RequestParam("criteria") int criteria,
                               @RequestParam("keyword") String keyword,
                               Model model,
                               Principal principal) {
        boolean userStatus = checkUserExistance(principal);
        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {

            List<User> allUsers = userService.getAllUsers();
            List<User> returnedUsers = filterUsers(allUsers, keyword, criteria, principal.getName());
            model.addAttribute("googleID", principal.getName());
            model.addAttribute("users", returnedUsers);

            return "search";
        }
    }

    private List<User> filterUsers(List<User> allUsers, String keyword, int criteria, String principalName) {
        List<User> returnedUsers = new ArrayList<>();
        for (User user : allUsers) {
            String firstName = user.getFirstName().toLowerCase();
            String lastName = user.getLastName().toLowerCase();
            String hobbies = user.getHobbies().toLowerCase();
            String cLvlLangs = user.getcLvlLangs().toLowerCase();
            String bLvlLangs = user.getbLvlLangs().toLowerCase();
            String aLvlLangs = user.getaLvlLangs().toLowerCase();
            keyword = keyword.toLowerCase();

            if (!principalName.equals(user.getId())) {
                System.out.println("principal name: " + principalName + "and userid: " + user.getId());
                if (criteria == 0) { // by names, hobbies, languages
                    if (firstName.contains(keyword) || lastName.contains(keyword) ||
                            hobbies.contains(keyword) || cLvlLangs.contains(keyword) ||
                            bLvlLangs.contains(keyword) || aLvlLangs.contains(keyword)) {
                        returnedUsers.add(user);
                    }
                } else if (criteria == 1) { // by names
                    if (firstName.contains(keyword) || lastName.contains(keyword)) {
                        returnedUsers.add(user);
                    }
                } else if (criteria == 2) { // by hobbies
                    if (hobbies.contains(keyword)) {
                        returnedUsers.add(user);
                    }
                } else if (criteria == 3) { // by languages
                    if (cLvlLangs.contains(keyword) || bLvlLangs.contains(keyword) ||
                            aLvlLangs.contains(keyword)) {
                        returnedUsers.add(user);
                    }
                }
            }

        }
        return returnedUsers;
    }

    @GetMapping("/messenger")
    public String messenger(Principal principal, Model model) {
        boolean userStatus = checkUserExistance(principal);

        if (userStatus) {
            model.addAttribute("userId", principal.getName());
            return "redirect:/signUp";
        } else {
            model.addAttribute("userId", principal.getName());
            return "messenger";
        }
    }

}