package org.kenux.security.controller.admin;

import lombok.RequiredArgsConstructor;
import org.kenux.security.domain.dto.RoleDto;
import org.kenux.security.domain.entity.Role;
import org.kenux.security.mapper.RoleMapper;
import org.kenux.security.service.RoleService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping(value = "/admin/roles")
    public String getRoles(Model model) throws Exception {
        List<Role> roles = roleService.getRoles();
        model.addAttribute("roles", roles);
        return "admin/role/list";
    }

    @GetMapping(value = "/admin/roles/register")
    public String viewRoles(Model model) throws Exception {
        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/role/detail";
    }

    @PostMapping(value = "/admin/roles")
    public String createRole(RoleDto roleDto) throws Exception {
        Role role = RoleMapper.INSTANCE.roleDtoToEntity(roleDto);
        roleService.createRole(role);

        return "redirect:/admin/roles";
    }

    @GetMapping(value = "/admin/roles/{id}")
    public String getRole(@PathVariable String id, Model model) throws Exception {
        Role role = roleService.getRole(Long.valueOf(id));
        model.addAttribute("role", role);
        return "admin/role/detail";
    }
}
