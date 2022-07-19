package com.revature.p1.web.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.revature.p1.orm.utils.ConnectionUtil;
import com.revature.p1.web.models.AvatarTypes;
import com.revature.p1.web.models.Avatars;
import com.revature.p1.web.models.Players;


public class PlayerPostgres {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Players create(Players players) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "insert into players " + "(id, full_name, username, password) " + "values (default, ?, ?, ?)";
			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, players.getFull_name());
			stmt.setString(2, players.getUsername());
			stmt.setString(3, players.getPassword());

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				players.setId(resultSet.getInt("id"));
				if (addPetsToUser(players, conn)) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} else {
				conn.rollback();
				return null;
			}

		} catch (SQLException e) {
			if (e.getMessage().contains("unique constraint")) {
				throw e;
			}
			e.printStackTrace();
		}

		return players;
	}

	@Override
	public Players findById(int id) {
		Players players = null;

		try (Connection conn = connUtil.getConnection()) {

			String sql = "select id, full_name, username, password from person where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				players = new Players();
				players.setId(id);
				players.setFull_name(resultSet.getString("full_name"));
				players.setUsername(resultSet.getString("username"));
				players.setPassword(resultSet.getString("password"));
				players.setPlayers(this.getAvatarsByPlayers(players, conn));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return players;
	}

	@Override
	public List<Players> findAll() {
		List<Players> players = new ArrayList<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id, full_name, username, password from players";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Players players = new Players();
				Players.setId(resultSet.getInt("id"));
				Players.setFull_name(resultSet.getString("full_name"));
				Players.setUsername(resultSet.getString("username"));
				Players.setPassword(resultSet.getString("password"));
				Players.setAvatars(this.getAvatarsByPlayers(players, conn));
				players.add(players);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	@Override
	public Players findByUsername(String username) {
		Players players = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id, full_name, username, password from person where username=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				players = new Players();
				players.setId(resultSet.getInt("id"));
				players.setPlayers(resultSet.getString("players"));
				players.setUsername(resultSet.getString("username"));
				players.setPassword(resultSet.getString("password"));
				players.setAvatars(this.getAvatarsByPlayers(players, conn));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		}

		return players;
	}
	
	private List<Avatars> getAvatarsByPlayers(Players players, Connection conn) throws SQLException {
		List<Avatars> avatars = new ArrayList<>();
		
		String sql = "select avatars.id, " 
				+ "avatars.name, " 
				+ "avatars.gender, "
				+ "avatars.eye_color, "
				+ "avatars.hair_color, "
				+ "avatars.shirt_color, "
				+ "avatars.pant_color, "
				+ "avatars.height, "
				+ "avatars.age, "
				+ "avatars.level, "
				+ "avatars.health, "
				//type_health, skill1, skill1damage, skill2, skill2damage
				+ "avatartypes.type_health as type_health, "
				+ "avatartypes.skill1 as skill1, "
				+ "Avatartypes.skill1damage as skill1damage, "
				+ "avatartypes.skill2 as skill2, "
				+ "avatartypes.skill2damage as skill2damage, "
				+ "from avatars "
				+ "join avatartypes on avatars.type_id = avatartypes.id " 
				+ "where players_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, players.getId());
		ResultSet resultSet = stmt.executeQuery();
		
		while (resultSet.next()) {
			Avatars avatars = new Avatars();
			String name = resultSet.getString("name");
			String userName = resultSet.getString("userName");
			//type_health, skill1, skill1damage, skill2, skill2damage
			Avatartypes avatartype = new Avatartypes(resultSet.getInt("type_id"),
					resultSet.getString("type_health"),
					resultSet.getString("skill1"),
					resultSet.getString("skill1damage"),
					resultSet.getString("skill2"),
					resultSet.getString("skill2damage"));
			

			avatars = new Avatars(name, userName, avatartype);
			avatars.setId(resultSet.getInt("id"));
			
			avatars.add(avatars);
		}
		
		return avatars;
	}
	
	private boolean addAvatarsToPlayers(Players players, Connection conn) throws SQLException {
		String sql = "select id from avatars where player_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, players.getId());
		
		ResultSet resultSet = stmt.executeQuery();
		List<Integer> existingIds = new ArrayList<>();
		while (resultSet.next()) {
			existingIds.add(resultSet.getInt("id"));
		}
		
		for (Avatars avatars : players.getAvatars()) {
			if (existingIds.contains(avatars.getId())) {
				sql = "update avatars set players_id=? where id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, players.getId());
				stmt.setInt(2, avatars.getId());
				
				int rowsUpdated = stmt.executeUpdate();
				if (rowsUpdated!=1) {
					return false;
				}
			}
		}
		return true;
	}

}

//need player_id in avatar
