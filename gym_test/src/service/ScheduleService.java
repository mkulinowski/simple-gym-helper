package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.GenericDAOImpl;
import entities.Schedule;

public class ScheduleService implements IService<Schedule> {

	private GenericDAOImpl<Schedule> genDaoImpl;
	
	public ScheduleService(){
			genDaoImpl = new GenericDAOImpl<Schedule>(Schedule.class);
	}
	
	
	@Override
	public void save(Schedule t) {
		genDaoImpl.add(t);
		
	}

	@Override
	public void update(Schedule t) {
		genDaoImpl.edit(t);
		
	}

	@Override
	public void delete(Integer id) {
		Schedule schedule = genDaoImpl.findByid(id);
		genDaoImpl.delete(schedule);
		
	}
    /**Additional method, schedule has PK in DB which is date type,
     * so to delete we will use findByDate() and put the returned schedule
     * to delete method from DAOImpl.
     */
	public void delete(String date){
		Schedule schedule = findByDate(date);
		genDaoImpl.delete(schedule);
	}

	@Override
	public List<Schedule> getAll() {
		// TODO Auto-generated method stub
		return genDaoImpl.getAll();
	}

	@Override
	public Schedule findByid(Integer id) {
		// TODO Auto-generated method stub
		return genDaoImpl.findByid(id);
	}
	
	/** Additional method. It has String type parameter because
	 * it is a problem with SimpleDateFormat to parse String from JTable(look at SchedulePanel at line 52)
	 * to identical date format like in Schedule class. Probably because in Java 8 it is better
	 * to use DateTime instead of Date which we have generated from DB by hibernate.
	 * So, we are comparing the String from selected row and date column, to parsed String
	 * from existing Schedule objects. As a primary key, we should get only one schedule object in a return.
	 */
	public Schedule findByDate(String class_date){
		List<Schedule> schedList = new ArrayList<Schedule>(); 
		schedList = getAll();
		for(Schedule schedule : schedList){
			if(schedule.getClassDate().toString().equals(class_date) ){
				
				return schedule;
			}
	}

		return null;
	}
}
