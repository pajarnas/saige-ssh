package com.lyn.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lyn.dao.TaskDao;
import com.lyn.model.OTask;
import com.lyn.model.OTaskTableView;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;
import com.lyn.model.enums.Stage;
import com.lyn.model.enums.TaskType;

/**
 * @author    Yaning Liu
 *
 * @filename  TaskDaoImpl.java
 *
 * @date      2019-02-16
 *
 */

@Repository
public class TaskDaoImpl implements TaskDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addTask(Task task) {
		sessionFactory.getCurrentSession().save(task);
	}

	public Task findTask(long id) {
		
		return (Task)sessionFactory.getCurrentSession().get(Task.class, id);
	}
	
public OTask findOTask(long id) {
		
		return (OTask)sessionFactory.getCurrentSession().get(OTask.class, id);
	}
	
public PTask findPTask(long id) {
		
		return (PTask)sessionFactory.getCurrentSession().get(PTask.class, id);
	}
	
	public void delTask(Task task) {
		sessionFactory.getCurrentSession().delete(task);
	}
	
	public void upadteTask(Task task) {
		  Session session = sessionFactory.openSession();
		    Transaction tx = session.beginTransaction();

		    //update/insert operations here

		    session.update(task);

		    tx.commit();

		    session.close();
		
	}
	


	
	@SuppressWarnings("unchecked")
	public List<Task> getTaskList() {
		
		Session s = this.sessionFactory.getCurrentSession();
//		 s.createSQLQuery("select _task.* FROM _task;")
		return (List<Task>)s.createQuery("select e FROM Task e").list();
		
	}

	/* (non-Javadoc)
	 * @see com.lyn.dao.TaskDao#addPTask(com.lyn.model.PTask)
	 */
	
	public void addPTask(PTask ptask) {
		sessionFactory.getCurrentSession().save(ptask);
		
	}

	public void addOTask(OTask otask) {
		sessionFactory.getCurrentSession().save(otask);
		
	}
	
	public List<OTaskTableView> getRelatedUser(User user) {
		
		
		    
		switch(user.getRole()){
			case 采购用料经理:
				List<PTask> ptasks = this.getPTaskList().stream()
                .filter(x -> x.getStage()==Stage.待用料||x.getStage()==Stage.待采购||x.getStage()==Stage.待采购入库)
                .collect(Collectors.toList()).stream()
		                .filter(x -> x.getUser() == user|| x.getUser()==null)
		                .collect(Collectors.toList());
				
					List<OTaskTableView> ptasklist = new ArrayList<OTaskTableView>();
					ptasks.forEach(item->{
						if(item.getUser()!=null) {
							ptasklist.add(new OTaskTableView(item,user));
						}else {
							ptasklist.add(new OTaskTableView(item,null));
						}
						
					});
					return ptasklist;
				      
			default:
				return null;
					
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PTask> getPTaskList(){
Session s = this.sessionFactory.getCurrentSession();
    
    		return (List<PTask>) s.createQuery("SELECT o FROM PTask o").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OTask> getOTaskList(){
Session s = this.sessionFactory.getCurrentSession();

    		return (List<OTask>) s.createQuery("SELECT o FROM OTask o WHERE task_type = '其他'").list();
	}

	/* (non-Javadoc)
	 * @see com.lyn.dao.TaskDao#getOTaskListByUser(com.lyn.model.User, com.lyn.model.PTask)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OTask> getOTaskListByUser(User user, PTask ptask) {
		Session s = this.sessionFactory.getCurrentSession();
		String hql="Select o from com.lyn.model.OTask o where ptask=:ptask and user=:user";
		 Query query = s.createQuery(hql);
         query.setParameter("ptask", ptask);
         query.setParameter("user", user);
         List<OTask> result = query.list();
		return result;
	}
}