package com.xu.service;

import com.xu.entity.PageInfo;
import com.xu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户Service层
 * @author Alkmg
 */
public interface UserService {
    /**
     * 通过账号和密码查询用户信息
     * @param user
     * @return
     */
    public User findUserByUsernameAndPassword(User user);
    /**获取总条数
     * @param username
     * @param id
     * @return
     */
    public Integer findTotalCount(@Param("username") String username, @Param("id") Integer id);

    /**
     * 分页查询用户信息
     * @param username
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageInfo<User> findUserList(@Param("username") String username, @Param("id") Integer id, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    /**
     * 添加管理员信息
     * @param user
     * @return
     */
    public int insertUser(User user);

    /**
     * 删除管理员信息
     * @param id
     * @return
     */
    public int deleteUser(Long id);

    /**
     * 修改管理员信息
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 根据用户的id查询用户的信息
     * @param id
     * @return
     */
    public User findUserById(Long id);

    /**
     * 查询用户表中的所有信息
     * @return
     */
    public List<User> findAll();

    /**
     * 查找用户的id和name映射
     * @return
     */
    public List<Map<Long,String>> findUserIdAndName();
}
