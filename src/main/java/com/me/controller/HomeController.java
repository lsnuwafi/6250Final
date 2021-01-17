package com.me.controller;

import com.me.pojo.User;
import com.me.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {
    private UserService userService;
    public HomeController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = {"index", "/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(@ModelAttribute("userForm") User user,Model model) {
        return "home";
    }

}
