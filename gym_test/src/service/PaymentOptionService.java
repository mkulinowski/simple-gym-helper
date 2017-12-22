package service;
import java.util.List;

import dao.GenericDAOImpl;
import entities.PaymentOption;

public class PaymentOptionService implements IService<PaymentOption>{

	private GenericDAOImpl<PaymentOption> genDaoImpl;
	
	public PaymentOptionService(){
		genDaoImpl = new GenericDAOImpl<PaymentOption>(PaymentOption.class);
	}
	
	@Override
	public void save(PaymentOption t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(PaymentOption t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		PaymentOption payOpt = genDaoImpl.findByid(id);
		genDaoImpl.delete(payOpt);
		
	}

	@Override
	public List<PaymentOption> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public PaymentOption findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}

}
