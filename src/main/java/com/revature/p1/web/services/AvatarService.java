package com.revature.p1.web.services;

import java.util.List;
import com.revature.p1.web.models.Avatar;

public interface AvatarService {
	public Avatar create(Avatar avatar);
	
	public Avatar findById(int id);
	
	public List<Avatar> findAll();

	public void update(Avatar avatar);

	public void delete(Avatar avatar);
}
