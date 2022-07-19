package com.revature.p1.web.services;


import java.util.List;

import com.revature.p1.web.models.Avatar;

import com.revature.p1.web.models.Player;

public interface PlayerService {
	
	public Player getPlayer(int id);
	
	
	public Player findById(int id);
	
	
	
	public List<Player> findAll();
	
	
	
	public Player findByUsername(String username);
	

	

	
}
