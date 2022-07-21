package com.revature.p1.web.data.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.orm.ORMQuery;
import com.revature.orm.ORMSession;
import com.revature.orm.ORMTransaction;
import com.revature.orm.session.SessionImpl;
import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.models.Player;

public class PlayerORM implements PlayerDAO {

	@Override
	public Player create(Player player) throws SQLException {
		ORMTransaction<Player> tx = null;
		try {
			ORMSession session = new SessionImpl();
			tx = session.beginTransaction(Player.class);
			tx = tx.addStatement("INSERT", player);
			tx.execute();
			tx.commit();
			int id = (int) tx.getGeneratedKeys().get(0);
			player.setId(id);
		} catch (SQLException e) {
			if (e.getMessage().contains("unique constraint")) {
				throw e;
			} else {
				if (tx !=null) {
					tx.rollback();
					tx.close();
				}
				e.printStackTrace();
			}
		}
		
		return player;
	}

	@Override
	public Player findById(int id) {
		Player player = null;
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Player> query = session.createQuery(Player.class);
			player = query.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public List<Player> findAll() {
		List<Player> players = new ArrayList<>();
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Player> query = session.createQuery(Player.class);
			players = query.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	@Override
	public void update(Player player) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Player> tx = session.beginTransaction(Player.class);
			tx.addStatement("UPDATE", player).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Player player) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Player> tx = session.beginTransaction(Player.class);
			tx.addStatement("DELETE", player).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Player findByUsername(String username) {
		Player player = null;
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Player> query = session.createQuery(Player.class);
			player = query.findOneBy("username", username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

}
