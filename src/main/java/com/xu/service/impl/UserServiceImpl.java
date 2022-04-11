package com.xu.service.impl;

import com.xu.dao.UserDao;
import com.xu.entity.PageInfo;
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
        return userDao.selectTotalCount(username,id);
    }

    @Override
    public PageInfo<User> findUserList(String username, Integer id, Integer currentPage, Integer pageSize) {
        PageInfo<User> pi = new PageInfo<User>();
        pi.setPageIndex(currentPage);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = userDao.selectTotalCount(username,id);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示管理员信息数
            //currentPage = (currentPage-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<User> adminList =	userDao.selectUserList(username,id,
                    (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(adminList);
        }
        return pi;
    }

    @Override
    public int insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public int updateUser(User user) {
        return  userDao.updateUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.selectUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }
}
