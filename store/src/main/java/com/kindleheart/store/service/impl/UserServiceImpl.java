package com.kindleheart.store.service.impl;

import com.kindleheart.store.dao.UserDao;
import com.kindleheart.store.dao.impl.UserDaoImpl;
import com.kindleheart.store.domain.User;
import com.kindleheart.store.service.UserService;

import java.sql.SQLException;

/**
 * Created by kindleheart happily.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void userRegister(User user) throws SQLException {
        //实现注册功能
        UserDao userDao = new UserDaoImpl();
        userDao.userRegister(user);
    }

    @Override
    public boolean userActive(String code) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.userActive(code);
        if(null != user) {
            user.setState(1);
            user.setCode(null);
            //更新操作
            userDao.updateUser(user);
            return true;
        } else {
            //没有查找到该用户
            return false;
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        userDao.updateUser(user);
    }

    @Override
    public User userLogin(User user) throws SQLException {
        UserDao userDao = new UserDaoImpl();
        User uu = userDao.userLogin(user);
        if(null == uu) {
            throw new RuntimeException("密码有误");
        } else if(uu.getState() == 0) {
            throw new RuntimeException("用户未激活");
        } else {
            return uu;
        }
    }
}
