package com.xu.service.impl;

import com.xu.dao.UserDao;
import com.xu.entity.User;
import com.xu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Alkmg
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByUsernameAndPassword(User user) {
        User userResult=userDao.selectUserByUsernameAndPassword(user);
        return userResult;
    }

    @Override
    public Integer findTotalCount(String username, Integer id) {
        return null;
    }

    @Override
    public List<User> findUserList(String username, Integer id, Integer currentPage, Integer pageSize) {
        return null;
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public User findUserById(Long id) {
        return userDao.selectUserById(id);
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
