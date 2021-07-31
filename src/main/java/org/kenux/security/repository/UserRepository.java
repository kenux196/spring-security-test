package org.kenux.security.repository;

import org.kenux.security.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    int countByUsername(String username);

    @Override
    void delete(Account account);
}
