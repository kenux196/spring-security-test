package org.kenux.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.entity.Account;
import org.kenux.security.domain.entity.Role;
import org.kenux.security.mapper.UserMapper;
import org.kenux.security.repository.RoleRepository;
import org.kenux.security.repository.UserRepository;
import org.kenux.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("userService")
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Account> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUser(Long id) {
        Account account = userRepository.findById(id).orElse(new Account());
        UserDto userDto = UserMapper.INSTANCE.accountToDto(account);

        List<String> roles = account.getUserRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
        userDto.setRoles(roles);
        return userDto;
    }

    @Override
    public void createUser(Account account) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userRepository.save(account);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
