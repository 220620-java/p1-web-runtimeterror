package com.revature.p1.web.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.p1.web.models.Trade;
import com.revature.p1.web.data.TradeDAO;
import com.revature.p1.web.orm.TradeORM;

public class TradeServImpl implements TradeService {
	//must go to model's ORM class!
	private static TradeDAO tradeDao = new TradeORM();

	@Override
	public Trade create(Trade trade) throws TradeAlreadyExistsException {
		// TODO Auto-generated method stub
		try {
			return tradeDao.create(trade);
		} catch (SQLException e) {
			//**********************************************************
			//throw new UsernameAlreadyExistsException();
			//**********************************************************
			//must not forget to uncomment this when its added!
		}
		return trade;
	}

	@Override
	public Trade findById(int id) {
		return tradeDao.findById(id);
	}

	@Override
	public List<Trade> findAll() {
		return tradeDao.findAll();
	}

	@Override
	public void update(Trade trade) {
		tradeDao.update(trade);
	}

	@Override
	public void delete(Trade trade) {
		// TODO Auto-generated method stub
		System.out.println("Cannot delete a trade.");
	}
}
