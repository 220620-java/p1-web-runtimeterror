package com.revature.p1.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
	private PlayerORM playerOrm;
	
	
	@Test
	public void findByUsername() {
		String name = "test"
		
		Player mockPlayer = new Player();
		Player returnPlayer = playerServ.findByUsername(returnPlayer);
		
		
	}
	
	@Test
	public void create() {
		Player mockPlayer = new Player();
		Mockito.when(playerOrm.create(mockPlayer)).thenReturn(mockPlayer);
	}
	
	@Test
	public void findById() {
		Player mockPlayer = new Player();
		
		return mockPlayer.findById();
	}
	
	@Test
	public void findAll()
	
	Mockito.when(playerOrm.f)
	
	{
		
	}
	
	@Test
	public void delete() {
		Player mockPlayer = new PlayerOrm();
		
	}
	
}
