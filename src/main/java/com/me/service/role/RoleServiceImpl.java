package com.me.service.role;

import com.me.pojo.Role;
import com.me.pojo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String roleName) {
        return roleRepository.findByName("ROLE_MANAGER");
    }
}
