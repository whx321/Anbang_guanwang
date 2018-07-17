package com.anbang.dao;

import org.springframework.stereotype.Repository;

import com.anbang.entity.User;

@Repository
public interface UserDao {

	public void saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(String username);

	public User queryUserByUsername(String username);

}
