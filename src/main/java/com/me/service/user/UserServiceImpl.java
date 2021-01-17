/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.service.user;

//import com.me.dao.UserDao;
import com.me.pojo.Role;
import com.me.pojo.RoleRepository;
import com.me.pojo.User;

import java.util.*;

import com.me.pojo.UserRepository;
import com.me.service.user.UserService;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 18572
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
    }

    @Override
    public void savetoRepository(User user) {
        userRepository.save(user);
    }

    @Override
    public void upgradeUser(User user) {

        Set<Role> set = user.getRoles();
        set.add(roleRepository.findByName("ROLE_MANAGER"));
        System.out.println(set);
        user.setRoles(set);
        userRepository.save(user);
    }

    @Override
    @Transactional
    @Modifying
    @NotFound(action= NotFoundAction.IGNORE)
    public void deleteUser(String username) {

        userRepository.delete(this.findByUsername(username).getId());
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean hasRole(User user, String role) {

        Role lookRole = roleRepository.findByName(role);
        return lookRole.getUsers().contains(user);

    }

    @Override
    public List<User> listUserByRole(String role){
        List<User> userList = userRepository.findAll();
        List<User> resList = new ArrayList<>();
        for (User user: userList){
            if (!this.hasRole(user, "ROLE_MANAGER") ){
                resList.add(user);
            }
        }

        return resList;

    }


}
