package com.revature.p1.web.orm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.orm.ORMQuery;
import com.revature.orm.ORMSession;
import com.revature.orm.ORMTransaction;
import com.revature.orm.session.SessionImpl;
import com.revature.p1.web.data.TradeDAO;
import com.revature.p1.web.models.Trade;

public class TradeORM implements TradeDAO {

	@Override
	public Trade create(Trade trade) {
		ORMTransaction<Trade> tx = null;
		try {
			ORMSession session = new SessionImpl();
			tx = session.beginTransaction(Trade.class);
			tx = tx.addStatement("INSERT", trade);
			tx.execute();
			tx.commit();
			int id = (int) tx.getGeneratedKeys().get(0);
			Trade.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trade;
	}

	@Override
	public void update(Trade trade) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Trade> tx = session.beginTransaction(Trade.class);
			tx.addStatement("UPDATE", trade).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(Trade trade) {
		try {
			ORMSession session = new SessionImpl();
			ORMTransaction<Trade> tx = session.beginTransaction(Trade.class);
			tx.addStatement("DELETE", trade).execute();
			tx.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Trade findById(int id) {
		Trade trade = null;
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Trade> query = session.createQuery(Trade.class);
			trade = query.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trade;

	}

	@Override
	public List<Trade> findAll() {
		List<Trade> trades = new ArrayList<>();
		try {
			ORMSession session = new SessionImpl();
			ORMQuery<Trade> query = session.createQuery(Trade.class);
			trades = query.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trades;

	}

}
