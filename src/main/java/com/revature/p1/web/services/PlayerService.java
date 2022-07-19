package com.revature.p1.web.services;

<<<<<<< HEAD
import java.util.List;

import com.revature.p1.web.models.Avatar;
=======
>>>>>>> de5321339fda449da72ed89f5cbc7ddd00956081
import com.revature.p1.web.models.Player;

public interface PlayerService {
	
	public Player registerUsername(Player player)throws UsernameAlreadyExistsException;
	
	
	public List<Avatar> viewAllAvatars();
	
	
	
	public Player getPlayer(int id);
	

	Player getPlayer(int id);

	Object viewAllPlayers();
	
	Player addPlayer(Player player);

	Player editPlayer(Player player);
}
