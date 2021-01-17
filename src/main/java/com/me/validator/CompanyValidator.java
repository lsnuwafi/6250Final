package com.me.validator;

import com.me.pojo.Company;
import com.me.pojo.User;
import com.me.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.concurrent.ConcurrentMap;

@Component
public class CompanyValidator implements Validator {

    private CompanyService companyService;
    @Autowired
    public CompanyValidator(CompanyService companyService){
        this.companyService = companyService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Company company = (Company) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (company.getName().length() < 6 || company.getName().length() > 32) {
            System.out.println("validating company name" + company.getName());
            errors.rejectValue("name", "Size.companyForm.comapnyname");
        }
        if (companyService.findByName(company.getName()) != null) {
            errors.rejectValue("name", "Duplicate.companyForm.comapnyname");
        }


    }
}
