package org.kenux.security.service;

public interface MethodSecurityService {

    void addMethodSecured(String resourceName, String roleName);

    void removeMethodSecured(String resourceName);
}
