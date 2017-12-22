package service;

import java.util.List;

import dao.GenericDAOImpl;
import entities.*;

public class ClassesService implements IService<Classes> {

	private GenericDAOImpl<Classes> genDaoImpl;
	
	public ClassesService(){
		genDaoImpl = new GenericDAOImpl<Classes>(Classes.class);
	}
	
	@Override
	public void save(Classes t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Classes t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Classes classes = genDaoImpl.findByid(id);
		genDaoImpl.delete(classes);
		
	}

	@Override
	public List<Classes> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Classes findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
