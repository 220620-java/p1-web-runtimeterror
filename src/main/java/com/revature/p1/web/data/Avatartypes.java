package com.revature.p1.web.data;

import java.util.List;

import com.revature.p1.web.models.Avatartypes;

public interface AvatartypesDAO extends DataAccessObject<Avatartypes>{
	public List<Avatartypes> findByType(Avatartypes avatartypes);
}
