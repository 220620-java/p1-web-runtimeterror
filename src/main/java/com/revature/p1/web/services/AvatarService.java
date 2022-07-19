package com.revature.p1.web.services;

import com.revature.p1.web.models.Avatar;

public interface AvatarService {

	public Avatar getAvatar(int id);
	
	public Avatar viewAllAvatars();

	public Avatar addAvatar(Avatar avatar);
	
	public Avatar editAvatar(Avatar avatar);
	
	public Avatar deleteAvatar(Avatar avatar); //or int deleteAvatar(int id);
	
}
