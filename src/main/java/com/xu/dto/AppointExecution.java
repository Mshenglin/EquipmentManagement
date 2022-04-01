package com.xu.dto;

import com.xu.entity.User;
import com.xu.enums.AppointStateEnum;

/**
 * 封装预约执行后结果
 */
public class AppointExecution {

	// 图书ID
	private long bookId;

	// 秒杀预约结果状态
	private int state;

	// 状态标识
	private String stateInfo;

	// 预约成功对象
	private User user;

	public AppointExecution() {
	}

	// 预约失败的构造器
	public AppointExecution(long bookId, AppointStateEnum stateEnum) {
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 预约成功的构造器
	public AppointExecution(long bookId, AppointStateEnum stateEnum, User user) {
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.user = user;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public User getuser() {
		return user;
	}

	public void setuser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "AppointExecution [bookId=" + bookId + ", state=" + state + ", stateInfo=" + stateInfo + ", user="
				+ user + "]";
	}

}
