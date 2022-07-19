package com.revature.p1.web.services;

import com.revature.p1.web.models.Player;

public interface PlayerService {

	Player getPlayer(int id);

	Object viewAllPlayers();
	
	Player addPlayer(Player player);

	Player editPlayer(Player player);
}
