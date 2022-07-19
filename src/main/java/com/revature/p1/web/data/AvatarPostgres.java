package com.revature.p1.web.data;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Trade;
import com.revature.p1.web.utils.ConnectionUtil;


public class AvatarPostgres implements AvatarDAO{
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public Avatar create(Avatar avatar) {
		try (Connection conn = connUtil.getConnection()) {

			conn.setAutoCommit(false);
			//id, avatar_name, gender, eye_color, hair_color, shirt_color, pant_color, height, age, level, health
			String sql = "insert into avatars "
					+ "(id, avatar_name, gender, eye_color, hair_color, shirt_color, pant_color, height, age, level, health) "
					+ "values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			String[] keys = {"id"};
			
			PreparedStatement stmt = conn.prepareStatement(sql, keys);
			stmt.setString(1, avatar.getAvatarName());
			stmt.setString(2, avatar.getGender());
			stmt.setString(3, avatar.getEyeColor());
			stmt.setString(4, avatar.getHairColor());
			stmt.setString(5, avatar.getShirtColor());
			stmt.setString(6, avatar.getPantColor());
			stmt.setInt(7, avatar.getHeight());
			stmt.setInt(8, avatar.getAge());
			stmt.setInt(9, avatar.getLevel());
			stmt.setInt(10, avatar.getHealth());
			
			int rowsAffected = stmt.executeUpdate();
			ResultSet resultSet = stmt.getGeneratedKeys();
			if (resultSet.next() && rowsAffected==1) {
				avatar.setId(resultSet.getInt("id"));
				conn.commit();
			} else {
				conn.rollback();
				return null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avatar;

	}
	
	@Override
	public Avatar findById(int id) {
		Avatar avatar = null;

		try (Connection conn = connUtil.getConnection()) {
			//id, avatar_name, gender, eye_color, hair_color, shirt_color, pant_color, height, age, level, health
			String sql = "select avatars.id, " 
					+ "avatars.avatar_name, " 
					+ "avatars.gender, " 
					+ "avatars.eye_color, "
					+ "avatars.hair_color, "
					+ "avatars.shirt_color, "
					+ "avatars.pant_color, "
					+ "avatars.height, "
					+ "avatars.age, "
					+ "avatars.level, "
					+ "avatars.health, "
					+ "avatartypes.id as avatartypes_id, "
					+ "avatartypes.trades as avatartypes_race, "
					+ "avatartypes.type_health as avatartypes_type_health, "
					+ "avatartypes.skill1 as trades_skill1, "
					+ "avatartypes.skill1damage as trades_skill1damage, "
					+ "avatartypes.skill2 as avatartypes_skill2, "
					+ "avatartypes.skill2damage as avatartypes_skill2damage, "
					+ "from avatars "
					+ "join avatartypes on avatars.avatartypes_id = avatars.id "
					+ "where avatars.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();
			
			//id, avatar_name, gender, eye_color, hair_color, shirt_color,
			//pant_color, height, age, level, health
			if (resultSet.next()) {
				String avatar_name = resultSet.getString("avatar_name");
				String gender = resultSet.getString("gender");
				String eye_color = resultSet.getString("eye_color");
				String hair_color = resultSet.getString("hair_color");
				String shirt_color = resultSet.getString("shirt_color");
				String pant_color = resultSet.getString("pant_color");
				int height = resultSet.getInt("height");
				int age = resultSet.getInt("age");
				int level = resultSet.getInt("level");
				int health = resultSet.getInt("health");
				
				Trade trade = new Trade(resultSet.getInt("avatars_id"),
						resultSet.getString("avatars_trades"),
						resultSet.getInt("avatars_type_health"),
						resultSet.getString("avatars_skill1"),
						resultSet.getInt("avatars_skill1damage"),
						resultSet.getString("avatars_skill2"),
						resultSet.getInt("avatars_skill2damage"));
				

				avatar = new Avatar(avatar_name, gender, eye_color, hair_color, shirt_color,
						pant_color, height, age, level, health, trade);
				avatar.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return avatar;

	}

	@Override
	public List<Avatar> findAll() {
		List<Avatar> avatar = new ArrayList<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select avatars.id, " 
					+ "avatars.avatar_name, " 
					+ "avatars.gender, " 
					+ "avatars.eye_color, "
					+ "avatars.hair_color, "
					+ "avatars.shirt_color, "
					+ "avatars.pant_color, "
					+ "avatars.height, "
					+ "avatars.age, "
					+ "avatars.level, "
					+ "avatars.health, "
					+ "avatartypes.id as avatartypes_id, "
					+ "avatartypes.trades as avatartypes_race, "
					+ "avatartypes.type_health as avatartypes_type_health, "
					+ "avatartypes.skill1 as avatartypes_skill1, "
					+ "avatartypes.skill1damage as avatartypes_skill1damage, "
					+ "avatartypes.skill2 as avatartypes_skill2, "
					+ "avatartypes.skill2damage as avatartypes_skill2damage, "
					+ "from avatars "
					+ "join avatartypes on avatars.avatartypes_id = avatars.id "
					+ "where avatars.id = ?";

			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String avatar_name = resultSet.getString("avatar_name");
				String gender = resultSet.getString("gender");
				String eye_color = resultSet.getString("eye_color");
				String hair_color = resultSet.getString("hair_color");
				String shirt_color = resultSet.getString("shirt_color");
				String pant_color = resultSet.getString("pant_color");
				int height = resultSet.getInt("height");
				int age = resultSet.getInt("age");
				int level = resultSet.getInt("level");
				int health = resultSet.getInt("health");
				
				Trade trade = new Trade(resultSet.getInt("avatars_id"),
						resultSet.getString("avatars_trades"),
						resultSet.getInt("avatars_type_health"),
						resultSet.getString("avatars_skill1"),
						resultSet.getInt("avatars_skill1damage"),
						resultSet.getString("avatars_skill2"),
						resultSet.getInt("avatars_skill2damage"));
				
				avatar = new Avatar(avatar_name, gender, eye_color, hair_color, shirt_color,
						pant_color, height, age, level, health, trade );
				avatar.setId(id);

				avatar.add(avatar);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avatar;
	}

	@Override
	public void update(Avatar avatar) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			//id, avatar_name, gender, eye_color, hair_color, shirt_color,
			//pant_color, height, age, level, health
			String sql = "update avatars "
					+ "set avatar_name=?, "
					+ "gender=?, "
					+ "eye_color=?, "
					+ "hair_color=?, "
					+ "shirt_color=?, "
					+ "pant_color=?, "
					+ "height=?, "
					+ "age=?, "
					+ "level=?, "
					+ "health=?, "
					+ "type_id=?"
					+ "where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, avatar.getAvatar_name());
			stmt.setString(2, avatar.getAvatar_gender());
			stmt.setString(3, avatar.getAvatar_eye_color());
			stmt.setString(4, avatar.getAvatar_hair_color());
			stmt.setString(5, avatar.getAvatar_shirt_color());
			stmt.setString(6, avatar.getAvatar_pant_color());
			stmt.setInt(7, avatar.getAvatar_height());
			stmt.setInt(8, avatar.getAvatar_age());
			stmt.setInt(9, avatar.getAvatar_level());
			stmt.setInt(10, avatar.getAvatar_health());
			stmt.setInt(11, avatar.getAvatar_type_id());
			stmt.setInt(12, avatar.getId());

			
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected<=1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Avatar avatar) {
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "delete from avatars where id=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, avatar.getId());
			
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected<=1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
