package org.kenux.security.mapper;

import org.kenux.security.domain.dto.RoleDto;
import org.kenux.security.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role roleDtoToEntity(RoleDto roleDto);

    RoleDto roleToDto(Role role);

}
