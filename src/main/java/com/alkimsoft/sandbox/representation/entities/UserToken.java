package com.alkimsoft.sandbox.representation.entities;

import com.alkimsoft.sandbox.representation.core.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by doğukan on 1.07.2016.
 */
@Entity
public class UserToken extends BaseEntity {
    @ManyToOne
    @JoinColumn(name ="userOid",referencedColumnName = "oid")
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
