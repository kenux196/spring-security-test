package org.kenux.security.repository;

import org.kenux.security.domain.entity.AccessIp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessIpRepository extends JpaRepository<AccessIp, Long> {

    AccessIp findByIpAddress(String ipAddress);
}
