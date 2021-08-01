package org.kenux.security.mapper;

import javax.annotation.Generated;
import org.kenux.security.domain.dto.UserDto;
import org.kenux.security.domain.dto.UserDto.UserDtoBuilder;
import org.kenux.security.domain.entity.Account;
import org.kenux.security.domain.entity.Account.AccountBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-01T15:59:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_275 (Azul Systems, Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public Account userDtoToEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        AccountBuilder account = Account.builder();

        account.username( userDto.getUsername() );
        account.email( userDto.getEmail() );
        account.age( userDto.getAge() );
        account.password( userDto.getPassword() );

        return account.build();
    }

    @Override
    public UserDto accountToDto(Account account) {
        if ( account == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.username( account.getUsername() );
        userDto.email( account.getEmail() );
        userDto.age( account.getAge() );
        userDto.password( account.getPassword() );

        return userDto.build();
    }
}
