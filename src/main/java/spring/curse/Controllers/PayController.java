package spring.curse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.curse.Models.User;
import spring.curse.Repos.UserRepos;

import java.security.Principal;

@Controller
public class PayController {
    @Autowired
    private final UserRepos userRepos;

    public PayController(UserRepos userRepos) {
        this.userRepos = userRepos;
    }

    @GetMapping("/pay/profile")
    public String payProfile(Model model, Principal principal){
        User user = userRepos.findByUsername(principal.getName());
        model.addAttribute("el", ((user.getCountElectricity() * 2) + ((user.getCountElectricity() * 2)/10)) + " тг");
        model.addAttribute("ho", ((user.getCountWaterHot() * 2) + ((user.getCountWaterHot() * 2)/10)) + " тг");
        model.addAttribute("co", ((user.getCountWaterCold() * 2) + ((user.getCountWaterCold() * 2)/10)) + " тг");
        model.addAttribute("tr", ((user.getCountTrash() * 2) + ((user.getCountTrash() * 2)/10)) + " тг");
        model.addAttribute("wtr", ((user.getCountWaterTrash() * 2) + ((user.getCountWaterTrash() * 2)/10)) + " тг");
        model.addAttribute("he", ((user.getCountHeating() * 2) + ((user.getCountHeating() * 2)/10)) + " тг");

        return "/pay/profile";
    }

    @PostMapping("/pay")
    public String pay(Model model, Principal principal){
        System.out.println("Hello user!");
        User user = userRepos.findByUsername(principal.getName());
        user.setCountElectricity((long)0);
        user.setCountWaterHot((long)0);
        user.setCountWaterCold((long)0);
        user.setCountTrash((long)0);
        user.setCountWaterTrash((long)0);
        user.setCountHeating((long)0);
        userRepos.save(user);
        return "redirect:/pay/profile";
    }
}
