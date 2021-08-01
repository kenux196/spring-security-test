package org.kenux.security.controller.login;

import lombok.RequiredArgsConstructor;
import org.kenux.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

//    private final UserService userService;
//    private final RoleService roleService;

    @GetMapping(value = "/denied")
    public String accessDenied() {
        return "user/login/denied";
    }
}
