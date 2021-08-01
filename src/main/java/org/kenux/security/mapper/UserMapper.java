package org.kenux.security.mapper;

import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    Account userDtoToEntity(UserDto userDto);

    UserDto accountToDto(Account account);
}
