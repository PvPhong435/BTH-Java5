package com.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab7.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String>{
	
	Account findByUsernameLike(String username);
}
