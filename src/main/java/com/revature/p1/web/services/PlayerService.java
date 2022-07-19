package com.revature.p1.web.services;

import java.util.List;

import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Player;

public interface PlayerService {
	
	public Player registerUsername(Player player)throws UsernameAlreadyExistsException;
	
	
	public List<Avatar> viewAllAvatars();
	
	
	
	public Player getPlayer(int id);
	

}
