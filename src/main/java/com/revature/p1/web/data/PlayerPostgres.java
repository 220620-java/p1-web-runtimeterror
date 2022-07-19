package com.revature.p1.web.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.revature.p1.web.utils.ConnectionUtil;
import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.models.Trade;


public class PlayerPostgres {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	public Player create(Player player) throws SQLException {
		try (Connection conn = connUtil.getConnection()) {

			conn.setAutoCommit(false);

			String sql = "insert into players " + "(id, full_name, username) " + "values (default, ?, ?)";
			String[] keys = { "id" };

			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, player.getName());
			stmt.setString(2, player.getUsername());

			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected == 1) {
				player.setId(resultSet.getInt("id"));
				if (addAvatarToPlayer(player, conn)) {
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

		return player;
	}

	@Override
	public Player findById(int id) {
		Player player = null;

		try (Connection conn = connUtil.getConnection()) {

			String sql = "select id, full_name, username from person where id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				player = new Player();
				player.setId(id);
				player.setName(resultSet.getString("full_name"));
				player.setUsername(resultSet.getString("username"));
				player.setPlayer(this.getAvatarsByPlayers(player, conn));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return players;
	}

	@Override
	public List<Player> findAll() {
		List<Player> player = new ArrayList<>();
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id, full_name, username from players";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				Player player = new Player();
				Player.setId(resultSet.getInt("id"));
				Player.setName(resultSet.getString("full_name"));
				Player.setUsername(resultSet.getString("username"));
				Player.setAvatars(this.getAvatarsByPlayers(player, conn));
				player.add(player);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}

	@Override
	public Player findByUsername(String username) {
		Player player = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select id, full_name, username from person where username=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				player = new Player();
				player.setId(resultSet.getInt("id"));
				player.setPlayer(resultSet.getString("players"));
				player.setUsername(resultSet.getString("username"));
				player.setAvatars(this.getAvatarsByPlayers(player, conn));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		}

		return player;
	}
	
	private List<Avatar> getAvatarsByPlayers(Player player, Connection conn) throws SQLException {
		List<Avatar> avatar = new ArrayList<>();
		
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
		stmt.setInt(1, player.getId());
		ResultSet resultSet = stmt.executeQuery();
		
		while (resultSet.next()) {
			Avatar avatar = new Avatar();
			String name = resultSet.getString("name");
			String userName = resultSet.getString("userName");
			//type_health, skill1, skill1damage, skill2, skill2damage
			Trade trade = new Trade(resultSet.getInt("type_id"),
					resultSet.getString("type_health"),
					resultSet.getString("skill1"),
					resultSet.getString("skill1damage"),
					resultSet.getString("skill2"),
					resultSet.getString("skill2damage"));
			

			avatar = new Avatar(name, userName, trade);
			avatar.setId(resultSet.getInt("id"));
			
			avatar.add(avatar);
		}
		
		return avatar;
	}
	
	private boolean addAvatarsToPlayers(Player player, Connection conn) throws SQLException {
		String sql = "select id from avatars where player_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, player.getId());
		
		ResultSet resultSet = stmt.executeQuery();
		List<Integer> existingIds = new ArrayList<>();
		while (resultSet.next()) {
			existingIds.add(resultSet.getInt("id"));
		}
		
		for (Avatar avatar : player.getAvatars()) {
			if (existingIds.contains(avatar.getId())) {
				sql = "update avatars set players_id=? where id=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, player.getId());
				stmt.setInt(2, avatar.getId());
				
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
