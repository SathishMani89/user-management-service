package com.terrabird.dao;

import com.terrabird.persistence.ContactDetails;
import com.terrabird.persistence.Quote;
import com.terrabird.persistence.TBUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author Praveer Das
 */

@Repository
public class UserDAO {

    @SuppressWarnings("entityManager")
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

    @Cacheable
    public Set<ContactDetails> getUserContactDetails(String userId) {
        TBUser tbUser = entityManager.find(TBUser.class, userId);
        return tbUser.getContacts();
    }

    @Cacheable
    public Set<Quote> getAllQuotesByUserId(String userId) {
        TBUser tbUser = entityManager.find(TBUser.class, userId);
        return tbUser.getQuoteSet();
    }
}
