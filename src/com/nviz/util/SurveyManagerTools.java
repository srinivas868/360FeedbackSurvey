package com.nviz.util;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nviz.vo.Survey;
import com.nviz.vo.User;

public class SurveyManagerTools {
	
	private Configuration cfg;
	private	SessionFactory sessionFactory;
	
	public SurveyManagerTools(){
		setConfiguration(new Configuration());
		getConfiguration().configure("/com/nviz/resource/hibernate.cfg.xml");
	}
	
	public List getItems(String pItemType) throws Exception {
		return getItems(pItemType,true);
	}
	
	/** this method will return item for given itemId
	 * @param classType
	 * @param pItemId
	 * @return
	 * @throws Exception
	 */
	public Object getItem(Class<Survey> classType, String pItemId) throws Exception {
		return getItem(classType, pItemId, true);
	}
	
	/** this method will return items for itemType
	 * @param pItemType
	 * @param pCloseSession
	 * @return
	 * @throws Exception
	 */
	public List getItems(String pItemType, boolean pCloseSession) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("FROM "+pItemType);
			return (List) query.list();
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			if(pCloseSession){
				session.close(); 
			}
		}
	}
	
	/** this method will return item for given itemId
	 * @param classType
	 * @param pItemId
	 * @param pCloseSession
	 * @return
	 * @throws Exception
	 */
	public Object getItem(Class<Survey> classType, String pItemId, boolean pCloseSession) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			return session.get(classType,Integer.valueOf(pItemId));
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			if(pCloseSession){
				session.close(); 
			}
		}
	}
	
	/**
	 * @param pItem
	 * @return
	 * @throws Exception
	 */
	public boolean addItem(Object pItem) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.persist(pItem);
			tx.commit();
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			session.close(); 
		}
		return true;
	}
	
	/**
	 * @param userName
	 * @param password
	 * @throws Exception 
	 */
	public User login(HttpServletRequest request) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			String userName = request.getParameter("login");
			String password = PasswordUtil.encrypt(request.getParameter("password"));
			Query query = session.createQuery("FROM User as u where u.login=:userName and u.password=:password");
			query.setString(SurveyConstants.USERNAME, userName);
			query.setString(SurveyConstants.PASSWORD, password);
			return (User) query.list().get(0);
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			session.close(); 
		}
	}
	
	public void logDebug(String message) {
		System.out.println(message);
	}
	
	public Configuration getConfiguration() {
		return cfg;
	}

	public void setConfiguration(Configuration cfg) {
		this.cfg = cfg;
	}

	public SessionFactory getSessionFactory() {
		if(this.sessionFactory == null){
			this.sessionFactory = getConfiguration().buildSessionFactory();
		}
		return sessionFactory;
	}
}