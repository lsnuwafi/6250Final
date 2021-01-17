/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.service.user;

//import com.me.dao.UserDao;
import com.me.pojo.Role;
import com.me.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 *
 * @author 18572
 */

public interface UserService {
    void save(User user);
    void savetoRepository(User user);
    void upgradeUser(User user);
    void deleteUser(String username);
    User findByUsername(String username);

    List<User> findAll();
    List<User> listUserByRole(String role);
    boolean hasRole(User user, String role);
}
