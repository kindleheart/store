package com.kindleheart.store.service;

import com.kindleheart.store.domain.User;

import java.sql.SQLException;

/**
 * Created by kindleheart happily.
 */
public interface UserService {
    void userRegister(User user) throws SQLException;
    boolean userActive(String code) throws SQLException;
    void updateUser(User user) throws SQLException;
    User userLogin(User user) throws SQLException;
}
