package com.revature.p1.web.data;

import com.revature.petapp.models;

public interface PlayersDAO extends DataAccessObject<Players>{
	public Players findByUsername(String username);

}