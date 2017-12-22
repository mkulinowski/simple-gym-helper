package service;

import java.util.List;

public interface IService<T> {
	
	public void save(T t);
	public void update(T t);
	public void delete(Integer id);
	public List<T> getAll();
	public T findByid(Integer id);

}
