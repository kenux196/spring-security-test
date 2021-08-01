package org.kenux.security.service;

import org.kenux.security.domain.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    void createRole(Role role);

    Role getRole(Long valueOf);
}
