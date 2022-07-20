package com.revature.p1.web.services;


import java.sql.Connection;
import java.util.List;

import com.revature.p1.web.models.Avatar;

import com.revature.p1.web.models.Player;
import com.revature.p1.web.models.Trade;

public interface UserService {
	
	
	public Player findById(int id);
	
	
	
	
	
	public List<Player> findAll();
	
	public Player create(Player player);
	
	
	
	public Player findByUsername(String username);
	

	

	
}
