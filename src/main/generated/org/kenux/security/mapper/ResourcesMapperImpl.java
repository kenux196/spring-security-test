package org.kenux.security.mapper;

import javax.annotation.Generated;
import org.kenux.security.domain.dto.ResourcesDto;
import org.kenux.security.domain.entity.Resources;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-01T22:51:36+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_275 (Azul Systems, Inc.)"
)
public class ResourcesMapperImpl implements ResourcesMapper {

    @Override
    public ResourcesDto resourcesToDto(Resources resources) {
        if ( resources == null ) {
            return null;
        }

        ResourcesDto resourcesDto = new ResourcesDto();

        return resourcesDto;
    }

    @Override
    public Resources resourceDtoToEntity(ResourcesDto resourcesDto) {
        if ( resourcesDto == null ) {
            return null;
        }

        Resources resources = new Resources();

        return resources;
    }
}
