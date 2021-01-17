package com.me.service.company;

import com.me.pojo.Company;
import com.me.pojo.User;

import java.util.List;
import java.util.Set;

public interface CompanyService {
    List<Company> showCompanyList();

    void save(Company company);

    Company findByName(String name);
}
