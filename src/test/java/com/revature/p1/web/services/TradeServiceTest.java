package com.revature.p1.web.services;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.p1.web.data.TradeDAO;
import com.revature.p1.web.exceptions.TradeAlreadyExistsException;
import com.revature.p1.web.models.Trade;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
	
	@InjectMocks
	private TradeService tradeServ = new TradeServImpl();
	
	@Mock
	private TradeDAO tradeDao;
	
	@Test
	public void create() throws TradeAlreadyExistsException {
		Trade mockTrade = new Trade();
		
		Mockito.when(tradeDao.create(mockTrade)).thenReturn(mockTrade);
		tradeServ.create(mockTrade);
		Mockito.verify(tradeDao, Mockito.times(1)).create(mockTrade);
	}
	
	@Test
	public void findById() {
		Trade mockTrade = new Trade();
		int id = 1;
		
		Mockito.when(tradeDao.findById(id)).thenReturn(mockTrade);
		tradeServ.findById(id);
		Mockito.verify(tradeDao, Mockito.times(id)).findById(id);
		
	}
	
	@Test
	public void findAll() {
		
		
		List<Trade> returnTrade = tradeServ.findAll();
		assertNotNull(returnTrade);
	}
	
	@Test
	public void update() {
		Trade mockTrade = new Trade();
		
		Mockito.when(tradeDao.create(mockTrade)).thenReturn(mockTrade);
		tradeServ.update(mockTrade);
		Mockito.verify(tradeDao, Mockito.times(1)).update(mockTrade);
	}
	
	
}
