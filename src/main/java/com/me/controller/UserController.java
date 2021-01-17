/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;


import com.me.pojo.User;
import com.me.service.*;
import com.me.service.company.CompanyService;
import com.me.service.role.RoleService;
import com.me.service.user.UserService;
import com.me.validator.CompanyValidator;
import com.me.validator.UserValidator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 18572
 */
@Controller
@RequestMapping("/user")
public class UserController {



    private UserService userService;
    private UserValidator userValidator;
    private UserDetailsService userDetailsService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, UserValidator userValidator,
                          SecurityService securityService, UserDetailsService userDetailsService){
        this.userService = userService;
        this.userValidator = userValidator;
        this.securityService = securityService;
        this.userDetailsService = userDetailsService;

    }

    @RequestMapping(value="/up", method = RequestMethod.POST)
    public String upgrade(@RequestParam("userName") String username, Model model){


        User user = userService.findByUsername(username);

        userService.upgradeUser(user);
        return "success";
    }

    @RequestMapping(value="/uppermition", method = RequestMethod.POST)
    public String uppermition(@RequestParam("selectedUser") String[] selectedUsers, Model model){

        for (String s: selectedUsers){
            User user = userService.findByUsername(s);
            userService.upgradeUser(user);
        }
        return "success";
    }

    @RequestMapping(value="/login")
    public String login(Model model, String error, String logout){

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        
        return "login";
    }

    @RequestMapping(value="/registManager")
    public String RegistManager(Model model){

        List<User> userList = userService.listUserByRole("ROLE_MANAGER");
        System.out.println(userList.size());
        model.addAttribute("userlist", userList);
        return "managerRegist";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(Model model) {
        List<User> userList = userService.findAll();
        System.out.println(userList.size());
        model.addAttribute("userlist", userList);
        return "deleteUser";
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestParam("deleteUser") String[] userList, Model model, Authentication auth) {

        for (String username: userList){
            userService.deleteUser(username);
        }

        return "success";
    }

    @RequestMapping(value = "/setInfo", method = RequestMethod.POST)
    public String setInfo(@ModelAttribute("userForm") User user,
                          Model model, Authentication auth) {
        UserDetail userDetail = (UserDetail) auth.getPrincipal();
        User updateUser = userService.findByUsername(userDetail.getUsername());
        if (!user.getFirst_name().equals(null)){
            updateUser.setFirst_name(user.getFirst_name());
            userDetail.setFirst_name(user.getFirst_name());
        }
        if (!user.getLast_name().equals(null)){
            updateUser.setLast_name(user.getLast_name());
            userDetail.setLast_name(user.getLast_name());
        }

        userService.savetoRepository(updateUser);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetail, auth.getCredentials(), auth.getAuthorities()));

        return "success";
    }


    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        return "redirect:/welcome";
    }





    @RequestMapping(value = "/403")
    public String accesssDenied(Model model) {
        model.addAttribute("status","Sorry, You do not have permission to access this page!");

        return "error";
    }

    @RequestMapping(value = "/404")
    public String notFind(Model model) {
        model.addAttribute("status","OOPS! The page seems gone!");

        return "error";
    }
   
}
