package org.kenux.security.service;

import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.entity.Account;

import java.util.List;

public interface UserService {

    List<Account> getUsers();
    UserDto getUser(Long id);
    void createUser(Account account);
    void deleteUser(Long id);
}
