package org.kenux.security.mapper;

import org.kenux.security.domain.dto.ResourcesDto;
import org.kenux.security.domain.entity.Resources;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResourcesMapper {

    ResourcesMapper instance = Mappers.getMapper(ResourcesMapper.class);

    ResourcesDto resourcesToDto(Resources resources);

    Resources resourceDtoToEntity(ResourcesDto resourcesDto);
}
