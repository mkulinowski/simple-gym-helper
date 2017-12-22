package dao;


import java.util.List;

import org.hibernate.Criteria;

public interface IGenericDAO<T> {
	
	public void add(T t);
	public void edit(T t);
	public void delete(T t);
	public List<T> getAll();
	public T findByid(Integer id);

	

}
