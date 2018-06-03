package com.terrabird.dao;

import com.terrabird.persistence.TBUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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

    @Transactional
    public void addUser(TBUser tbUser) {
        entityManager.persist(tbUser);
    }

    @Transactional
    public void updatePassword(TBUser tbUser) {
        Query query = entityManager.createQuery(
                "UPDATE TBUser SET password = :newPassword WHERE userId = :userId");
        query.setParameter("newPassword", tbUser.getPassword());
        query.setParameter("userId", tbUser.getUserId());
        query.executeUpdate();
    }
}
