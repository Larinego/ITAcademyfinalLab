package com.larinego.web.controllers;

import com.larinego.dao.entities.User;
import com.larinego.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping(path = "")
public class WelcomeController {

    @Autowired
    UserService userService;


    @RequestMapping(value={"", "/"}, method = RequestMethod.GET)
    public String welcome() {

        return "redirect:/en/products";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewLogin(Model model) {
        User user = new User();
        model.addAttribute("command", user);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String validateRegister(@Valid @ModelAttribute("command") User userForm,
                                   BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }


        userForm.setRole("USER");
        userService.save(userForm);

        return "redirect:/loginto";
    }

    @RequestMapping(value = "/loginto", method = RequestMethod.GET)
    public String loginPage(Model model, String loginFailure){

        if(loginFailure != null){
            model.addAttribute("loginFailure", "Incorrect login or password");
        }
        return "loginto";
    }

}
