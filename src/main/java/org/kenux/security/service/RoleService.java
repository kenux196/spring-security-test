package org.kenux.security.service;

import lombok.extern.slf4j.Slf4j;
import org.kenux.security.domain.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    void createRole(Role role);

    Role getRole(Long valueOf);
}
