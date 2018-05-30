package com.terrabird.dao;

import com.terrabird.persistence.TBUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Praveer Das
 */

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<TBUser> findAllUsers() {
        Query query = entityManager.createQuery("select user from TBUser user");
        return (List<TBUser>) query.getResultList();
    }
}
