package spring.curse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import spring.curse.Models.User;
import spring.curse.Repos.UserRepos;

import java.security.Principal;

@ControllerAdvice
public class UserControllerAdvice {
    @Autowired
    private UserRepos userRepos;

    @ModelAttribute
    public void userAdvice(Model model, Principal principal){
        if(principal != null){
            model.addAttribute("activeUser", principal.getName());
            User user = userRepos.findByUsername(principal.getName());
            model.addAttribute("userRole", user.isAdmin());
            model.addAttribute("signOut", true);
        }else {
            model.addAttribute("activeUser", "<none>");
            model.addAttribute("signOut", false);
        }
    }
}
