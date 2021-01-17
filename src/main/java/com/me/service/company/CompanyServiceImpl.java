package com.me.service.company;

import com.me.pojo.Company;
import com.me.pojo.CompanyRepository;
import com.me.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;


    @Override
    public List<Company> showCompanyList() {
        return companyRepository.findAll();
    }

    @Override
    public void save(Company company) {
        company.setName(company.getName());
        company.setManager(company.getManager());
        company.setUsers(new HashSet<User>());
        companyRepository.save(company);
    }

    @Override
    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }
}
