package com.nviz.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nviz.util.PasswordUtil;
import com.nviz.util.SurveyConstants;

public class UserDAO {

	private static final String USER_ID = "userId";
	private static final String USER = "user";
	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
	String updateErr = "userUpdateError";
	String userErr = "Error while updating user in USER_MASTER @ChangePassword block:";

	/**
	 * This method is used to return the user details in Map if available,
	 * otherwise it returns null
	 * 
	 * @param parameterMap
	 * @return
	 */
	public Map<String, Object> userLogIn(String userName , String password) {
		Configuration cfg=new Configuration();  
	    cfg.configure("/com/nviz/resource/hibernate.cfg.xml");  
	    SessionFactory sf=cfg.buildSessionFactory();  
	    Session session=sf.openSession();  
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		User user = new User();
		if (userName != null && password != null && !userName.isEmpty() && !password.isEmpty()) {
			if (null != session) {
				try {
					password = PasswordUtil.encrypt(password);
					Query query = null;
					query = session
							.createQuery("FROM User as nu where nu.userName=:username and nu.password=:password");
					query.setString(SurveyConstants.USERNAME, userName);
					query.setString(SurveyConstants.PASSWORD, password);
					List userList = query.list();
					if(userList != null && !userList.isEmpty()) {
						user = (User) userList.get(0);
						userMap.put(USER, user);
						userMap.put(USER_ID, user.getId());
					} else {
						LOG.error("Error while fetching user in USER_MASTER");
					}

				} catch (Exception e) {
					LOG.error("Error while fetching user in USER_MASTER", e); 
					

				} 
				finally {
					if(session != null && session.isOpen()){
						//CrawlUtil.closeSession(session);
					}
				}
			} else {
				LOG.error("Unable to get connection from DB: Please try after some time....");
			}
		} else {
			LOG.error("provide user CREDENTIALS");
		}
		return userMap;
	}
}
