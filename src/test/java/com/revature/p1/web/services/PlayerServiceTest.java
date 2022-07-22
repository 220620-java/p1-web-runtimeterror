package com.revature.p1.web.services;

import static org.mockito.ArgumentMatchers.anyInt;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import com.revature.p1.web.data.PlayerDAO;
import com.revature.p1.web.exceptions.UsernameAlreadyExistsException;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.orm.PlayerORM;
import com.revature.p1.web.services.PlayerServImpl;
import com.revature.p1.web.services.PlayerService;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {
	//mmkay....timmmmy!
	
	@InjectMocks
	private PlayerService playerServ = new PlayerServImpl();
	
	@Mock
	private PlayerDAO playerDao;
	
	@Test
	public void findByUsername() throws UsernameAlreadyExistsException {
		//Player mockPlayer = new Player(username, "test");
		String username = "mock";
		Player mockPlayer = new Player();
		
		Mockito.when(playerDao.findbyUsername(username)).thenReturn(mockPlayer);
		playerServ.findByUsername(username);
		Mockito.verify(playerDao, Mockito.times(1)).findByUsername(username);
		
	}
	
	@Test
	public void create() throws UsernameAlreadyExistsException {
		Player mockPlayer = new Player();
		Mockito.when(playerDao.create(mockPlayer)).thenReturn(mockPlayer);
		playerServ.create(mockPlayer);
		Mockito.verify(playerDao, Mockito.times(1)).create(mockPlayer);
	}
	
	@Test
	public void findById() {
		Player mockPlayer = new Player();
		int id = 1;
		
		Mockito.when(playerDao.findById(id)).thenReturn(mockPlayer);
		playerServ.findById(id);
		Mockito.verify(playerDao, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void findAll()  {
		
		String username = "test1";
		
		Mockito.when(playerDao.findByUsername(username)).thenReturn(new Player());
		
		List<Player> returnPlayer = playerServ.findAll();
		
		assertNotNull(returnPlayer);
	}
	
	@Test
	public void delete() {
		Player mockPlayer = new Player();
		
		playerServ.delete(mockPlayer);
		Mockito.verify(playerDao, Mockito.times(1)).delete(mockPlayer);
		
	}
	
}
