package com.revature.p1.web.data;

import java.util.List;
import com.revature.p1.web.models.Trade;

public interface TradeDAO extends DataAccessObject<Trade>{
	public Trade findById(int id);
	
	public List<Trade> findAll();
}
