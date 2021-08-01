package org.kenux.security.controller.user;

import lombok.RequiredArgsConstructor;
import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.entity.Account;
import org.kenux.security.mapper.UserMapper;
import org.kenux.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
//    private final RoleService roleService;

    @GetMapping(value = "/users")
    public String createUser() throws Exception {
        return "user/login/register";
    }

    @PostMapping(value = "/users")
    public String createUser(UserDto userDto) throws Exception {
        Account account = UserMapper.INSTANCE.userDtoToEntity(userDto);
        userService.createUser(account);
        return "redirect:/";
    }
}
