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
				player.setAvatars(this.getAvatarsByPlayers(player, conn));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return player;
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
				player.setName(resultSet.getString("players"));
				player.setUsername(resultSet.getString("username"));
				player.setAvatars(this.getAvatarsByPlayers(player, conn));
			}

		} catch (SQLException e) {
			//e.printStackTrace();
		}

		return player;
	}
	
	private List<Avatar> getAvatarsByPlayers(Player player, Connection conn) throws SQLException {
		List<Avatar> avatars = new ArrayList<>();
		
		String sql = "select avatars.id, " 
				+ "avatars.avatar_name, "
				+ "avatars.level, "
				+ "avatars.health, "
				+ "trades.trade_name as trade, "
				+ "from avatars "
				+ "join trades on avatars.trade_id = trades.id " 
				+ "where players.avatar_id=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, player.getAvatars(avatars[0].get));
		ResultSet resultSet = stmt.executeQuery();
		
		while (resultSet.next()) {
			Avatar avatar = new Avatar();
			String avatarName = resultSet.getString("avatar_name");
			int avatarLevel = resultSet.getInt("level");
			int avatarHealth = resultSet.getInt("health");
			Trade trade = new Trade(resultSet.getInt("id"),
					resultSet.getString("trade_name"));
			

			avatar = new Avatar(avatarName, avatarLevel, avatarHealth, trade);
			avatar.setId(resultSet.getInt("id"));
			
			avatars.add(avatar);
		}
		
		return avatars;
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
