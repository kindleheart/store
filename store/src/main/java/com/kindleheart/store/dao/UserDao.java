package com.kindleheart.store.dao;

import com.kindleheart.store.domain.User;

import java.sql.SQLException;

/**
 * Created by kindleheart happily.
 */
public interface UserDao {
    void userRegister(User user) throws SQLException;
    User userActive(String code) throws SQLException;
    void updateUser(User user) throws SQLException;
    User userLogin(User user) throws SQLException;
}
