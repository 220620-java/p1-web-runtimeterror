package com.revature.p1.web.services;

import com.revature.p1.web.models.Avatar;

public interface AvatarService {

	Avatar getAvatar(int id);
	
	Object viewAllAvatars();

	Avatar addAvatar(Avatar avatar);
	
	Avatar editAvatar(Avatar avatar);
	
	Avatar deleteAvatar(Avatar avatar); //or int deleteAvatar(int id);
	
}
