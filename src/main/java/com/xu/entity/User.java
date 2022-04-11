package com.xu.entity;

import java.util.Objects;

/**
 *用户操作实体类
 * @author mashenglin
 */
public class User {
    /**
     * 用户id
     */
     private Long id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private Long phone;
    /**
     * 部门
     */
    private String department;
    /**
     * 注册时间
     */
    private Long createTime;
    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 用户类型
     */
    private Integer userRole;

    public User() {
    }

    public User(Long id, String username, String password, String name, Long phone, String studentId, String department, Long createTime, Long updateTime, Integer userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.userRole = userRole;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }
        User user = (User) o;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword()) && getName().equals(user.getName()) && getPhone().equals(user.getPhone()) && getDepartment().equals(user.getDepartment()) && getCreateTime().equals(user.getCreateTime()) && getUpdateTime().equals(user.getUpdateTime()) && getUserRole().equals(user.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getName(), getPhone(), getDepartment(), getCreateTime(), getUpdateTime(), getUserRole());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", department='" + department+ '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", userRole=" + userRole +
                '}';
    }
}
