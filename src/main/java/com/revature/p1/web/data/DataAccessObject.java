package com.revature.p1.web.data;

import java.sql.SQLException;
import java.util.List;
public interface DataAccessObject<T> {
	public T create(T t) throws SQLException;
	
	public T findById(int id);
	
	public List<T> findAll();

	public void update(T t);

	public void delete(T t);

}
