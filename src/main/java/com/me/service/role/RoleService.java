package com.me.service.role;

import com.me.pojo.Role;

public interface RoleService {
    Role findByName(String roleName);
}
