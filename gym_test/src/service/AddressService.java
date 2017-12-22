package service;

import java.util.List;

import dao.GenericDAOImpl;
import entities.Address;


public class AddressService implements IService<Address> {

	private GenericDAOImpl<Address> genDaoImpl;
	
	public AddressService(){
		genDaoImpl = new GenericDAOImpl<Address>(Address.class);
	}
	
	@Override
	public void save(Address t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Address t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Address address = genDaoImpl.findByid(id);
		genDaoImpl.delete(address);
		
	}

	@Override
	public List<Address> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Address findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
