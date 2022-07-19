package com.revature.p1.web.data;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Trade;
import com.revature.p1.web.utils.ConnectionUtil;

public class TradePostgres {
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public Trade findById(int id) {
		Trade trade = null;

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select trade.id, " 
					+ "trade.trade, "
					+ "trade.trade_health, " 
					+ "trade.skill1, "
					+ "trade.skill1damage, "
					+ "trade.skill2, "
					+ "trade.skill2damage, "
					+ "from trades"
					+ "where trade.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet resultSet = stmt.executeQuery();
			
			//id, avatar_name, gender, eye_color, hair_color, shirt_color,
			//pant_color, height, age, level, health
			if (resultSet.next()) {
				String tradeName = resultSet.getString("trade_name");
				String skill1 = resultSet.getString("skill1");
				String skill2 = resultSet.getString("skill2");
				int tradeHealth = resultSet.getInt("trade_health");
				int skill1damage = resultSet.getInt("skill1damage");
				int skill2damage = resultSet.getInt("skill2damage");
				
				trade = new Trade(id, tradeName, tradeHealth, skill1, skill1damage, skill2, skill2damage);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return trade;

	}

	@Override
	public List<Trade> findAll() {
		List<Trade> trades = new ArrayList<>();
		Trade trade = new Trade();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select trade.id, " 
					+ "trade.trade_name, "
					+ "trade.trade_health, " 
					+ "trade.skill1, "
					+ "trade.skill1damage, "
					+ "trade.skill2, "
					+ "trade.skill2damage, "
					+ "from trades"
					+ "where trade.id = ?";


			Statement stmt = conn.createStatement();

			ResultSet resultSet = stmt.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String tradeName = resultSet.getString("trade_name");
				String skill1 = resultSet.getString("skill1");
				String skill2 = resultSet.getString("skill2");
				int tradeHealth = resultSet.getInt("trade_health");
				int skill1damage = resultSet.getInt("skill1damage");
				int skill2damage = resultSet.getInt("skill2damage");
				
				trade = new Trade(id, tradeName, tradeHealth, skill1, skill1damage, skill2, skill2damage );
				trade.setId(id);

				trades.add(trade);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trades;
	}
}
