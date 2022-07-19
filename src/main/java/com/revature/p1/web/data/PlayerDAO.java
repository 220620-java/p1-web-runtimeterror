package com.revature.p1.web.data;

import com.revature.p1.web.models.Player;

public interface PlayerDAO extends DataAccessObject<Player>{
	public Player findByUsername(String username);
	
	//createPlayer
	
	//updatePlayer
	
	//deletePlayer
}