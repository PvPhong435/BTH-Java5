package com.assignment.caulong.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.caulong.models.Account;

public interface AccountDAO extends JpaRepository<Account, Integer>{
	Account findByUsername(String username);
}
