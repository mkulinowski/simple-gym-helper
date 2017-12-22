package service;
import entities.*;

import java.util.List;

import dao.*;

public class CustomerService implements IService<Customer> {

	private GenericDAOImpl<Customer> genDaoImpl;
	
	public CustomerService(){
		
		genDaoImpl = new GenericDAOImpl<Customer>(Customer.class);
		
	}

	@Override
	public void save(Customer t) {
		// TODO Auto-generated method stub
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Customer t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Customer customer = genDaoImpl.findByid(id);
		genDaoImpl.delete(customer);
		
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Customer findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}
	
}


