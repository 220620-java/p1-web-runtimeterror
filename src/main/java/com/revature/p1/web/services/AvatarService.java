package com.revature.p1.web.services;

import java.sql.SQLException;
import java.util.List;
import com.revature.p1.web.models.Avatar;

public interface AvatarService {
	public Avatar create(Avatar avatar) throws SQLException;
	
	public Avatar findById(int id);
	
	public List<Avatar> findAll();

	public void update(Avatar avatar);

	public void delete(Avatar avatar);
}
