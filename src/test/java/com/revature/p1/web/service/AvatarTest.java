package com.revature.p1.web.service;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.p1.web.data.AvatarDAO;
import com.revature.p1.web.models.Avatar;
import com.revature.p1.web.models.Player;
import com.revature.p1.web.services.AvatarServImpl;
import com.revature.p1.web.services.AvatarService;

@ExtendWith(MockitoExtension.class)
public class AvatarTest {
	
	@InjectMocks
	private AvatarService avatarServ = new AvatarServImpl();
	
	@Mock 
	private AvatarDAO avatarDao;
	
	@Test 
	public void create() {
		
		Avatar mockAvatar = new Avatar();
		Mockito.when(avatarDao.create(mockAvatar)).thenReturn(mockAvatar);
		avatarServ.create(mockAvatar);
		Mockito.verify(avatarDao, Mockito.times(1)).create(mockAvatar);
	}
	
	@Test
	public void findById() {
		Avatar mockAvatar = new Avatar();
		int id = 1;
		
		Mockito.when(avatarDao.findById(id)).thenReturn(mockAvatar);
		avatarServ.findById(id);
		Mockito.verify(avatarDao, Mockito.times(id)).findById(id);
		
	}
	
	@Test
	public void findAll() {
		
		
		
		List<Avatar> returnAvatar = avatarServ.findAll();
		assertNotNull(returnAvatar);
	}
	
	@Test
	public void update() {
		Avatar mockAvatar = new Avatar();
		
		Mockito.when(avatarDao.create(mockAvatar)).thenReturn(mockAvatar);
		avatarServ.update(mockAvatar);
		Mockito.verify(avatarDao, Mockito.times(1)).update(mockAvatar);
	}
	
	@Test
	public void delete() {
		Avatar mockAvatar = new Avatar();
		
		avatarServ.delete(mockAvatar);
		Mockito.verify(avatarDao, Mockito.times(1)).delete(mockAvatar);
		
	}

}
