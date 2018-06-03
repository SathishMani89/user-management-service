package com.terrabird.service;

import com.terrabird.dao.UserDAO;
import com.terrabird.persistence.TBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public void addUser(TBUser tbUser) {
        String encryptedPassword = getEncryptedPassword(tbUser.getPassword());
        tbUser.setPassword(encryptedPassword);
        userDAO.addUser(tbUser);
    }

    public void updatePassword(TBUser tbUser) {
        String encryptedPassword = getEncryptedPassword(tbUser.getPassword());
        tbUser.setPassword(encryptedPassword);
        userDAO.updatePassword(tbUser);
    }

    private String getEncryptedPassword(String password) {
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    password.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < encodedHash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedHash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
        } catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return hexString.toString();
    }
}
