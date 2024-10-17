package com.Thi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thi.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String>{
	
	Account findByUsernameLike(String username);
}
