package com.revature.p1.web.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.exceptions.UsernameAlreadyExistsException;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.orm.PlayerORM;

public class PlayerServImpl implements PlayerService {
	
	private static PlayerDAO playerDao = new PlayerORM();

	@Override
	public Player findByUsername(String username) {
		return playerDao.findByUsername(username);
		
			
	}

	@Override
	public Player create(Player player) throws UsernameAlreadyExistsException {
		/*
		 * try { player = playerDao.create(player); } catch (SQLException e) { throw new
		 * UsernameAlreadyExistsException();
		 * 
		 * } return player;
		 */
		/*
		 * if(playerDao.findByUsername(null).equals(player)) { throw new
		 * UsernameAlreadyExistsException(); } else { return player; }
		 */
		if(playerDao.findByUsername(player.getUsername()).equals(null)){
			return player;
		} else {
			throw new UsernameAlreadyExistsException(null, null, null);
		}
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
