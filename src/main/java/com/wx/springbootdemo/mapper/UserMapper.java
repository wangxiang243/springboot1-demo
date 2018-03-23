package com.wx.springbootdemo.mapper;

import com.wx.springbootdemo.entity.User;

import java.util.List;

public interface UserMapper {
	
	List<User> getAll();

	User getOne(Long id);

	void insert(User user);

	void update(User user);

	void delete(Long id);

}