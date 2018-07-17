package com.anbang.dao;

import org.springframework.stereotype.Repository;

import com.anbang.entity.Admin;

@Repository
public interface AdminDao {

	public void addAdmin(Admin admin);

	public void updateAdmin(Admin admin);

	public void deleteAdmin(String account);

	public Admin queryAdmin(String account, String password);
	
}
