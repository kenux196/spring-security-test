package org.kenux.security.service.impl;

import org.kenux.security.domain.entity.Resources;
import org.kenux.security.service.ResourcesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Override
    public List<Resources> selectResources() {
        return null;
    }

    @Override
    public void insertResources(Resources resources) {

    }

    @Override
    public void deleteResources(Long valueOf) {

    }

    @Override
    public Resources selectResources(Long valueOf) {
        return null;
    }
}
