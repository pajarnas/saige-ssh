package com.lyn.dao.impl;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lyn.dao.TaskDao;
import com.lyn.model.OTask;
import com.lyn.model.PTask;
import com.lyn.model.Task;
import com.lyn.model.User;
import com.lyn.model.enums.Role;

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
	
	public User getRelatedUser(PTask ptask,Role role) {
		Session s = this.sessionFactory.getCurrentSession();
		switch(role){
			case 采购用料经理:
					List<OTask> prtasks = ptask.getPrtasks();
				    if(prtasks.isEmpty()) {
				    	return null;
				    }
				    else {
				 	   return prtasks.get(0).getUser();
				    }
				      
			default:
				return null;
					
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<PTask> getPTaskList(){
Session s = this.sessionFactory.getCurrentSession();
    
    		return (List<PTask>) s.createSQLQuery("SELECT _task.*,_ptask.* FROM _task LEFT OUTER JOIN _ptask ON _ptask.taskid=_task .id WHERE task_type = '生产';").addEntity(PTask.class).list();
	}
}
