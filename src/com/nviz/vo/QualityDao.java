package com.nviz.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

/*<NVIZION SOLUTIONS PVT LTD>
*
*	Copyright (C) 2004-2005 NVIZION SOLUTIONS PVT LTD. 
*	All Rights Reserved.  No use, copying or distribution 
*	of this work may be made except in accordance with a 
*	valid license agreement from My Company.  This notice 
*	must be included on all copies, modifications and 
*	derivatives of this work. 
*
*	NVIZION SOLUTIONS PVT LTD MAKES NO REPRESENTATIONS OR WARRANTIES 
*	ABOUT THE SUITABILITY OF 	THE SOFTWARE, EITHER EXPRESS OR 
*	IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED 
*	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
*	PURPOSE, OR NON-INFRINGEMENT. MY COMPANY SHALL NOT BE 
*	LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT 
*	OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
*	DERIVATIVES. 
*
*</NVIZION SOLUTIONS PVT LTD>*/

public class QualityDao {
	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);

	/**
	 * This method is used to add the quality in QUALITY table
	 * @param title
	 * @param description
	 * @return
	 */
	public boolean addQuality(JSONObject qualityJson) {

		Configuration cfg = new Configuration();
		cfg.configure("com/nviz/resource/hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		boolean result = false;
		
		if (qualityJson != null && qualityJson.has("quality") && qualityJson.has("description")) {
			try {
				if (null != session) {
					tx = session.beginTransaction();
					
					QualityMasterBean quality = new QualityMasterBean();
					quality.setTitle(qualityJson.getString("quality"));
					quality.setDescription(qualityJson.getString("description"));
					
					session.save(quality);
					tx.commit();
					
					result = true;

				} else {
					LOG.error("Unable to get connection from DB: Please try after some time....");
				}
			} catch (Exception e) {
				LOG.error("Error while fetching user in USER_MASTER", e);

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		} else {
			LOG.error("provide user details");
		}
		return result;
	}

	public boolean createSurvey(JSONObject surveyJson) {
		Configuration cfg = new Configuration();
		cfg.configure("com/nviz/resource/hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		boolean result = false;
		
		if (surveyJson != null && surveyJson.has("surveyTitle") && surveyJson.has("qualities")) {
			try {
				if (null != session) {
					tx = session.beginTransaction();
					
					Survey survey = new Survey();
					survey.setTitle(surveyJson.getString("surveyTitle"));
					
					Set<SurveyQuality> qualitySet = new HashSet<SurveyQuality>();
					if(surveyJson.has("qualities")) {
						JSONArray qualities = surveyJson.getJSONArray("qualities");
						for(int i=0; i < qualities.length(); i++) {
							Query query = session.createQuery("from QualityMasterBean where title=:title");
							query.setParameter("title", qualities.getString(i));
							if(query.list() != null 
									&& query.list().get(0) != null){
								SurveyQuality surveyQuality = new SurveyQuality();
								QualityMasterBean qMaster = (QualityMasterBean) query.list().get(0);
								surveyQuality.setTitle(qMaster.getTitle());
								surveyQuality.setDescription(qMaster.getDescription());
								
								session.save(surveyQuality);
								qualitySet.add(surveyQuality);
								
							}
						}
					}
					survey.setQualities(qualitySet);
					
					session.save(survey);
					tx.commit();
					
					result = true;

				} else {
					LOG.error("Unable to get connection from DB: Please try after some time....");
				}
			} catch (Exception e) {
				LOG.error("Error while fetching user in USER_MASTER", e);

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		} else {
			LOG.error("provide user details");
		}
		return result;
	}
	
	public List<String> getQualities() {
		Configuration cfg=new Configuration();  
	    cfg.configure("com/nviz/resource/hibernate.cfg.xml");  
	    SessionFactory sf=cfg.buildSessionFactory();  
	    Session session=sf.openSession();  
	    Transaction tx=session.beginTransaction(); 
		List<String> qualityList = new ArrayList<String>();
		try {
			Query query = session.createQuery("select title from QualityMasterBean");
			qualityList = query.list();
		} catch (Exception e) {
			LOG.error("Error while fetching user in USER_MASTER", e);
		}
		finally {
			if(session != null && session.isOpen()){
				session.close();
				session = null;
			}
		}
		return qualityList;
	}

	public boolean createDesignation(JSONObject dJson) {

		Configuration cfg = new Configuration();
		cfg.configure("com/nviz/resource/hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		boolean result = false;
		
		if (dJson != null && dJson.has("designation")) {
			try {
				if (null != session) {
					tx = session.beginTransaction();
					
					Role role = new Role();
					role.setRoleName(dJson.getString("designation"));
					
					session.save(role);
					tx.commit();
					
					result = true;

				} else {
					LOG.error("Unable to get connection from DB: Please try after some time....");
				}
			} catch (Exception e) {
				LOG.error("Error while fetching user in USER_MASTER", e);

			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		} else {
			LOG.error("provide user details");
		}
		return result;
	}

}
