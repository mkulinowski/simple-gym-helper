package service;
import java.util.List;

import dao.GenericDAOImpl;
import entities.Payment;

public class PaymentService implements IService<Payment> {

	private GenericDAOImpl<Payment> genDaoImpl;	
	
	public PaymentService(){
		genDaoImpl = new GenericDAOImpl<Payment>(Payment.class);
	}
	
	@Override
	public void save(Payment t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Payment t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Payment payment = genDaoImpl.findByid(id);
		genDaoImpl.delete(payment);
		
	}

	@Override
	public List<Payment> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Payment findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
