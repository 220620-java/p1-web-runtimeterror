package com.revature.p1.web.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.p1.web.data.AvatarDAO;
import com.revature.p1.web.orm.AvatarORM;
import com.revature.p1.web.models.Avatar;

public class AvatarServImpl implements AvatarService {
	
	private static AvatarDAO avatarDao = new AvatarORM();

	@Override
	public Avatar create(Avatar avatar) throws SQLException {
		
		return avatarDao.create(avatar);
	}

	@Override
	public Avatar findById(int id) {
		return avatarDao.findById(id);
	}

	@Override
	public List<Avatar> findAll() {
		return avatarDao.findAll();
	}

	@Override
	public void update(Avatar avatar) {
		avatarDao.update(avatar);
		
	}

	@Override
	public void delete(Avatar avatar) {
		avatarDao.delete(avatar);
		
	}

}
