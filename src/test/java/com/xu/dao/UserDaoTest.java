package com.xu.dao;

import java.util.List;

import com.xu.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.entity.User;

public class UserDaoTest extends BaseTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void testQueryById() throws Exception {
		long userId = 1;
		User user = userDao.selectUserById(userId);
		System.out.println(user.toString());
	}
@Test
	public void selectUserByUsernameAndPassword(){
		User user=new User();
		user.setUsername("mashenglin");
		user.setPassword("123");
	System.out.println(userDao.selectUserByUsernameAndPassword(user));

}

}
