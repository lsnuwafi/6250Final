package com.me.controller;

import com.me.pojo.Company;
import com.me.pojo.User;
import com.me.service.user.UserService;
import com.me.service.company.CompanyService;
import com.me.validator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private CompanyService companyService;
    private CompanyValidator companyValidator;
    private UserService userService;
    @Autowired
    public CompanyController(CompanyService companyService, CompanyValidator companyValidator, UserService userService){
        this.companyService = companyService;
        this.companyValidator = companyValidator;
        this.userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showCompany(Model model) {
        List<Company> companyList = companyService.showCompanyList();

        model.addAttribute("companylist", companyList);

        return "company";
    }

    @RequestMapping(value = {"/workstation"}, method = RequestMethod.GET)
    public String workstation(Model model) {
        System.out.println("working station");
        return "ws1";
    }


    @RequestMapping(value = "/startupregistration", method = RequestMethod.GET)
    public String startupregistration(Model model) {
        model.addAttribute("companyForm", new Company());

        return "startupregistration";
    }
    @RequestMapping(value = {"/startupregistration"}, method = RequestMethod.POST)
    public String startupregistration(@ModelAttribute("companyForm") Company company, BindingResult bindingResult, Principal principal, Model model) {
        companyValidator.validate(company, bindingResult);
        if (bindingResult.hasErrors()) {
            return "startupregistration";
        }
        if (principal != null){
            String name = principal.getName();
            User user = userService.findByUsername(name);
            company.setManager(user);
            companyService.save(company);
        }

        return "success";
    }

}
