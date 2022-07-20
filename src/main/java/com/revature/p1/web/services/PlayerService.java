package com.revature.p1.web.services;

import java.sql.Connection;
import java.util.List;

import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.models.Trade;

public interface PlayerService {
	//should only have a get method; cannot add, update, or delete (table is set in db)
	
	public List<Trade> findAll();
	
	//private List<Avatar> getAvatarsByPlayers(Player player, Connection conn);
	
	public Trade findById(int id);
}
