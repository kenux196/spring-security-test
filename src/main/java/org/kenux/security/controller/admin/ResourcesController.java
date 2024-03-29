package org.kenux.security.controller.admin;

import lombok.RequiredArgsConstructor;
import org.kenux.security.domain.dto.ResourcesDto;
import org.kenux.security.domain.entity.Resources;
import org.kenux.security.domain.entity.Role;
import org.kenux.security.mapper.ResourcesMapper;
import org.kenux.security.repository.RoleRepository;
import org.kenux.security.service.MethodSecurityService;
import org.kenux.security.service.ResourcesService;
import org.kenux.security.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleRepository roleRepository;
    private final MethodSecurityService methodSecurityService;
    private final RoleService roleService;

    @GetMapping(value = "/admin/resources")
    public String getResources(Model model) throws Exception {

        List<Resources> resources = resourcesService.selectResources();
        model.addAttribute("resources", resources);

        return "admin/resource/list";
    }

    @PostMapping(value = "/admin/resources")
    public String createResources(ResourcesDto resourcesDto) throws Exception {

        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        Resources resources = ResourcesMapper.instance.resourceDtoToEntity(resourcesDto);
        resources.setRoleSet(roles);

        resourcesService.insertResources(resources);
        methodSecurityService.addMethodSecured(resourcesDto.getResourceName(), resourcesDto.getRoleName());

        return "redirect:/admin/resources";
    }

    @GetMapping(value = "/admin/resources/register")
    public String viewRoles(Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = new Resources();
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value = "/admin/resources/{id}")
    public String getResources(@PathVariable String id, Model model) throws Exception {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = resourcesService.selectResources(Long.valueOf(id));
        model.addAttribute("resources", resources);

        return "admin/resource/detail";
    }

    @GetMapping(value = "/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id, Model model) throws Exception {

        Resources resources = resourcesService.selectResources(Long.valueOf(id));
        resourcesService.deleteResources(Long.valueOf(id));
        methodSecurityService.removeMethodSecured(resources.getResourceName());

        return "redirect:/admin/resources";
    }
}
