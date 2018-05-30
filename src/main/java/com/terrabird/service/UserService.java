package com.terrabird.service;

import com.terrabird.dao.UserDAO;
import com.terrabird.persistence.TBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Praveer Das
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public List<TBUser> getAllUsersInSystem() {
        return userDAO.findAllUsers();
    }
}
