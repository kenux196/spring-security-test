package org.kenux.security.controller.admin;

import lombok.RequiredArgsConstructor;
import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.entity.Account;
import org.kenux.security.domain.entity.Role;
import org.kenux.security.mapper.UserMapper;
import org.kenux.security.service.RoleService;
import org.kenux.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserManagerController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(value = "/admin/users")
    public String getUsers(Model model) throws Exception {
        List<Account> accounts = userService.getUsers();
        model.addAttribute("users", accounts);
        return "admin/user/list";
    }

    @PostMapping(value = "/admin/users")
    public String createUser(UserDto userDto) throws Exception {

        Account account = UserMapper.INSTANCE.userDtoToEntity(userDto);
        userService.createUser(account);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/admin/users/{id}")
    public String getUser(@PathVariable(value = "id") Long id, Model model) {
        UserDto userDto = userService.getUser(id);
        List<Role> roleList = roleService.getRoles();

        model.addAttribute("act", (id > 0) ? "modify" : "add");
        model.addAttribute("user", userDto);
        model.addAttribute("roleList", roleList);

        return "admin/user/detail";
    }

    @GetMapping(value = "/admin/users/delete/{id}")
    public String removeUser(@PathVariable(value = "id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
