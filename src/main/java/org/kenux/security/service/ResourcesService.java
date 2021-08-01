package org.kenux.security.service;

import org.kenux.security.domain.entity.Resources;

import java.util.List;

public interface ResourcesService {
    List<Resources> selectResources();

    void insertResources(Resources resources);

    void deleteResources(Long valueOf);

    Resources selectResources(Long valueOf);
}
