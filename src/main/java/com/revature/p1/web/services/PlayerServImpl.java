package com.revature.p1.web.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.orm.PlayerORM;

public class PlayerServImpl implements PlayerService {
	
	private static PlayerDAO playerDao = new PlayerORM();

	@Override
	public Player findByUsername(String username) throws UsernameAlreadyExistsException{
		try {
			return playerDao.findByUsername(username);
		} catch (SQLException e) {
			return new UsernameAlreadyExistsException();
		}
	}

	@Override
	public Player create(Player player) {
		
		return	playerDao.create(player);
			
		
		
	}

	@Override
	public Player findById(int id) {
		return playerDao.findById(id);
	}

	@Override
	public List<Player> findAll() {
		return playerDao.findAll();
	}

	@Override
	public void update(Player player) {
		playerDao.update(player);
		
	}

	@Override
	public void delete(Player player) {
		playerDao.delete(player);
		
	}

}
