package service;
import java.util.List;

import dao.GenericDAOImpl;
import entities.*;
/*
 * It's a view from database, so there will be only method listAll(),
 * just to import data from table.
 */
public class ClassScheduleIdViewService {

	private GenericDAOImpl<ClassScheduleId> genDaoImpl;
	
	public ClassScheduleIdViewService(){
		genDaoImpl = new GenericDAOImpl<ClassScheduleId>(ClassScheduleId.class);
	}
	
	public List<ClassScheduleId> getAll(){
		return genDaoImpl.getAll();
	}
}


