package com.terrabird.controller;

import com.terrabird.persistence.TBUser;
import com.terrabird.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Praveer Das
 */
@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    public List<TBUser> getAllUsers(ModelMap modelMap) {
        List<TBUser> userList = userService.getAllUsersInSystem();
        modelMap.put("UserList", userList);
        for(TBUser tbUser : userList) {
            log.debug(tbUser.getUserId() + " :: " + tbUser.getUserPreference());
        }
        return userList;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.PUT)
    public ResponseEntity<TBUser> addUser(@RequestBody TBUser tbUser) {
        if(null != tbUser) {
            userService.addUser(tbUser);
        }
        return new ResponseEntity<TBUser>(tbUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public ResponseEntity<TBUser> updatePassword(@RequestBody TBUser tbUser) {
        if(null != tbUser) {
            userService.updatePassword(tbUser);
        }
        return new ResponseEntity<TBUser>(tbUser, HttpStatus.OK);
    }


}
