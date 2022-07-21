package com.revature.p1.web.services;

import java.util.List;

import com.revature.p1.web.models.Trade;

public interface TradeService {
public Trade create(Trade trade);
	
	public Trade findById(int id);
	
	public List<Trade> findAll();

	public void update(Trade trade);

	public void delete(Trade trade);
}

//orm implements dao interfaces
//then these service classes call those dao methods
//then servlets/delegate calls these services
//woo