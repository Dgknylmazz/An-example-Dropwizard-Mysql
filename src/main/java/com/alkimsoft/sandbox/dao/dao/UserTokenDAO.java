package com.alkimsoft.sandbox.dao.dao;

import com.alkimsoft.sandbox.dao.core.BaseDAO;
import com.alkimsoft.sandbox.representation.entities.UserToken;
import org.hibernate.SessionFactory;

/**
 * Created by doğukan on 1.07.2016.
 */
public class UserTokenDAO extends BaseDAO<UserToken> {
    public UserTokenDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

}
