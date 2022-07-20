package com.revature.p1.web.services;

import java.util.List;

import com.revature.p1.web.data.AvatarDAO;
import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Player;

public class UserServiceImpl implements UserService {
	private PlayerDAO playerDao;
	private AvatarDAO avatarDao;

	@Override
	public Player findById(int id) {
		
		return playerDao.findById(id);
	}

	

	@Override
	public List<Player> findAll() {
		
		return playerDao.findAll();
	}

	@Override
	public Player create(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Avatar addAvatar(Avatar avatar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findByUsername(String username) {
		
		return null;
	}

}
