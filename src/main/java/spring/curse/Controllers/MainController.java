package spring.curse.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.curse.Models.User;
import spring.curse.Service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserService userService;


    @GetMapping()
    public String mainPage(){
        return "/mein";
    }

    @GetMapping("/mein")
    public String mainPageTwo(){
        return "/mein";
    }

    @GetMapping("/reg")
    public String reg(){
        System.out.println("GetReg");
        return "reg";
    }
    @PostMapping("reg")
    public String addUser(
            @Valid @ModelAttribute("newUser") User user,
            BindingResult bindingResult,
            Model model){

        if (bindingResult.hasErrors()) {
            System.out.println("ERROR BINDING!");
            Map<String, String> errors = UtilsController.getErrorMap(bindingResult);

            model.mergeAttributes(errors);

            return "reg";
        }
        if(!userService.addUser(user)){
            model.addAttribute("message", "User is found!");
            return "reg";
        }
        System.out.println("PostReg");
        return "redirect:mein";
    }
}
