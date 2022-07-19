package com.revature.p1.web.services;

import java.util.List;

import com.revature.p1.web.models.Trade;

public interface TradeService {
	//should only have a get method; cannot add, update, or delete (table is set in db)
	
	public List<Trade> findAll();
	
	
	
	public Trade findById(int id);
}
