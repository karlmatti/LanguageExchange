package language.exchange.langex;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("eventName", "FIFA 2018");
        System.out.println(model.toString());
        return "index";
    }

    @PostMapping("/")
    public String postIndex(@RequestParam("text") String text, Model model2) {
        model2.addAttribute("info", text);
        System.out.println(text);
        return "index";
    }
}