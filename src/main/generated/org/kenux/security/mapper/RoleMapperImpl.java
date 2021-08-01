package org.kenux.security.mapper;

import javax.annotation.Generated;
import org.kenux.security.domain.dto.RoleDto;
import org.kenux.security.domain.entity.Role;
import org.kenux.security.domain.entity.Role.RoleBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-01T22:39:38+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_275 (Azul Systems, Inc.)"
)
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role roleDtoToEntity(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        RoleBuilder role = Role.builder();

        return role.build();
    }

    @Override
    public RoleDto roleToDto(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        return roleDto;
    }
}
