package service;
import java.util.List;

import dao.GenericDAOImpl;
import entities.Staff;

public class StaffService implements IService<Staff> {
	
	private GenericDAOImpl<Staff> genDaoImpl;

	public StaffService(){
		genDaoImpl = new GenericDAOImpl<Staff>(Staff.class);
	}
	
	@Override
	public void save(Staff t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Staff t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Staff staff = genDaoImpl.findByid(id);
		genDaoImpl.delete(staff);
		
	}

	@Override
	public List<Staff> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Staff findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
