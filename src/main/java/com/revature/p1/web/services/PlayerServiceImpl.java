package com.revature.p1.web.services;

import java.util.List;

import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.data.TradeDAO;

import com.revature.p1.web.models.Trade;

public class PlayerServiceImpl implements PlayerService {

	private PlayerDAO playerDao;
	private TradeDAO tradeDao;

	@Override
	public List<Trade> findAll() {
		
		return tradeDao.findAll();
		
		
	}

	@Override
	public Trade findById(int id) {
		
		return tradeDao.findById(id);
	}
	
	
	
	
}
