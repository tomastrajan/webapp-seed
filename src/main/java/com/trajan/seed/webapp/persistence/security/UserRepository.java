package com.trajan.seed.webapp.persistence.security;

import com.trajan.seed.webapp.domain.security.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);

}
