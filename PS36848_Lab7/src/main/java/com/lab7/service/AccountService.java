package com.lab7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab7.dao.AccountDAO;
import com.lab7.entity.Account;

@Service
public class AccountService {
	@Autowired
	AccountDAO accDao;
	
	public Account getUser(String username)
	{
		return accDao.findByUsernameLike(username);
	}
	
	public Boolean checklogin(Account account)
	{
		Account accountGet=this.getUser(account.getUsername());
		if(accountGet!=null)
		{
			if(accountGet.getPassword().equals(account.getPassword()))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
	}
}
