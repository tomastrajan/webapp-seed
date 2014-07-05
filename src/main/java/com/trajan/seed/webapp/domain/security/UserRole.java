package com.trajan.seed.webapp.domain.security;

import com.trajan.seed.webapp.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-05
 */
@Entity
public class UserRole extends BasicEntity {

    @Enumerated(EnumType.STRING)
    private UserRoleName name;

    public UserRoleName getName() {
        return name;
    }

    public void setName(UserRoleName name) {
        this.name = name;
    }

}
