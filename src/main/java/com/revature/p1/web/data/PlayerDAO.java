package com.revature.p1.web.data;

import java.sql.SQLException;
import java.util.List;

import com.revature.p1.web.models.Player;

public interface PlayerDAO extends DataAccessObject<Player>{
	
	public Player findByUsername(String username);
	
	public Player create(Player player) throws SQLException;
	
	public Player findById(int id);
	
	public List<Player> findAll();

	public void update(Player player);

	public void delete(Player player);
	
}