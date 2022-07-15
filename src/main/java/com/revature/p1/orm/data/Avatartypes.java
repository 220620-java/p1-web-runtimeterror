package com.revature.p1.orm.data;

import java.util.List;
import com.revature.p1.orm.models.Avatartypes;

public interface AvatartypesDAO extends DataAccessObject<Avatartypes>{
	public List<Avatartypes> findByType(Avatartypes avatartypes);
}
