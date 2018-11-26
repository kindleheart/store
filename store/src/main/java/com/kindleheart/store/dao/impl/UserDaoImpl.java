package com.kindleheart.store.dao.impl;

import com.kindleheart.store.dao.UserDao;
import com.kindleheart.store.domain.User;
import com.kindleheart.store.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by kindleheart happily.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void userRegister(User user) throws SQLException {
        //用户注册
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into user values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode()};
        runner.update(sql, params);
    }

    @Override
    public User userActive(String code) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where code = ?";
        return runner.query(sql, new BeanHandler<>(User.class), code);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "update user set username = ?, password = ?, name = ?, email = ?, telephone = ?, birthday = ?," +
                "sex =  ?, state = ?, code = ? where uid = ?";
        Object[] params = {user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
                user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(), user.getUid()};
        runner.update(sql, params);
    }

    @Override
    public User userLogin(User user) throws SQLException {
        QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        return runner.query(sql, new BeanHandler<>(User.class), user.getUsername(), user.getPassword());
    }
}
