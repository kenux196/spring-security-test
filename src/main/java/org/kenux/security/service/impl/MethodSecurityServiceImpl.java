package org.kenux.security.service.impl;

import org.kenux.security.service.MethodSecurityService;
import org.springframework.stereotype.Service;

@Service
public class MethodSecurityServiceImpl implements MethodSecurityService {
    @Override
    public void addMethodSecured(String resourceName, String roleName) {

    }

    @Override
    public void removeMethodSecured(String resourceName) {

    }
}
