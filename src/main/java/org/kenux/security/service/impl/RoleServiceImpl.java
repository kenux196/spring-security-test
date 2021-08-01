package org.kenux.security.service.impl;

import org.kenux.security.domain.entity.Role;
import org.kenux.security.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Override
    public void createRole(Role role) {

    }

    @Override
    public Role getRole(Long valueOf) {
        return null;
    }
}
