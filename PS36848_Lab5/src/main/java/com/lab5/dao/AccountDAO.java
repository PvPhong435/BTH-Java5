package com.lab5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab5.entity.Account;


public interface AccountDAO extends JpaRepository<Account, String>{
}
