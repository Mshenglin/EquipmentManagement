package com.xu.dao;


import com.xu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO层接口
 * @author mashenglin
 */
public interface UserDao {
    /**
     * 通过账号和密码查询用户信息
     * @param user
     * @return
     */
    public User selectUserByUsernameAndPassword(User user);
    /**获取总条数
     * @param username
     * @param id
     * @return
     */
    public Integer selectTotalCount(@Param("username") String username, @Param("id") Integer id);

    /**
     * 分页查询用户信息
     * @param username
     * @param id
     * @param currentPage
     * @param pageSize
     * @return
     */
    public List<User> selectUserList(@Param("username") String username, @Param("id") Integer id, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

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
    public User selectUserById(Long id);

    /**
     * 查询用户表中的所有信息
     * @return
     */
    public List<User> selectAll();

    /**
     * 查询用户表id和name的映射
     * @return
     */
    public List<Map<Long,String>> selectUserIdAndUserName();
}
