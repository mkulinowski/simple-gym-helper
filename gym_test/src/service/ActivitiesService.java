package service;

import java.util.List;

import dao.GenericDAOImpl;
import entities.Activities;


public class ActivitiesService implements IService<Activities> {

	private GenericDAOImpl<Activities> genDaoImpl;
	
	
	public ActivitiesService(){
		genDaoImpl = new GenericDAOImpl<Activities>(Activities.class);
	}
	
	@Override
	public void save(Activities t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Activities t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Activities activities = genDaoImpl.findByid(id);
		genDaoImpl.delete(activities);
		
	}

	@Override
	public List<Activities> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Activities findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
