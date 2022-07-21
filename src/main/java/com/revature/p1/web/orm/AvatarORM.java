package com.revature.p1.web.data.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.orm.ORMQuery;
import com.revature.orm.ORMSession;
import com.revature.orm.ORMTransaction;
import com.revature.orm.session.SessionImpl;
import com.revature.p1.web.data.AvatarDAO;
import com.revature.p1.web.models.Avatar;

public class AvatarORM implements AvatarDAO{

	@Override
	public Avatar create(Avatar avatar) throws SQLException{
		try {
			ORMSession session = new SessionImpl();
			tx = session.beginTransaction(Avatar.class);
			tx = tx.addStatement("INSERT", avatar);
			tx.execute();
			tx.commit();
			int id = (int) tx.getGeneratedKeys().get(0);
			avatar.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avatar;

	}

	@Override
	public Avatar findById(int id) {
		Avatar avatar = null;
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Avatar> query = session.createQuery(Avatar.class);
			avatar = query.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avatar;

	}

	@Override
	public List<Avatar> findAll() {
		List<Avatar> avatars = new ArrayList<>();
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Avatar> query = session.createQuery(Avatar.class);
			avatars = query.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avatars;

	}

	@Override
	public void update(Avatar avatar) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Avatar> tx = session.beginTransaction(Avatar.class);
			tx.addStatement("UPDATE", avatar).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

	@Override
	public void delete(Avatar avatar) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Avatar> tx = session.beginTransaction(Avatar.class);
			tx.addStatement("DELETE", avatar).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
