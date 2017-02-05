package com.nviz.vo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyDAO {
	private static final Logger LOG = LoggerFactory.getLogger(UserDAO.class);
	String updateErr = "userUpdateError";
	String userErr = "Error while updating user in USER_MASTER @ChangePassword block:";
	private Configuration cfg;
	
	public SurveyDAO(){
		setConfiguration(new Configuration());
		getConfiguration().configure("com/nviz/resource/hibernate.cfg.xml");
	}

	private Survey surveyOperation(Survey survey, List surveyList) {
		Iterator userIterator = surveyList.iterator();
		try {
			while (userIterator.hasNext()) {
				survey = (Survey) userIterator.next();

				System.out.println("surveyList:" + survey.getSurveyId() + ""
						+ survey.getTitle() + "" + survey.getDescription());
				survey.setSurveyId(survey.getSurveyId());
				survey.setTitle(survey.getTitle());
				survey.setDescription(survey.getDescription());
				System.out.println("survey values are setted properly");
			}
		} catch (Exception e) {
			LOG.error("error: ", e);
		}
		return survey;
	}
	
	public Survey getSurvey(String userName, String password) {
		List<Survey> survey = new ArrayList<>();
		SessionFactory sf = getConfiguration().buildSessionFactory();
		Session session = sf.openSession();
		Query query = session
				.createQuery("FROM Survey");
		survey = (List<Survey>) query.list();
		return survey.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<User> getListofUsers(int userId) {
		List<User> users = new ArrayList<User>();
		SessionFactory sf = getConfiguration().buildSessionFactory();
		Session session = sf.openSession();
		Query query = session
				.createQuery("FROM User as nu where not  nu.userId=:userId)");
		query.setInteger("userId", userId);
		users = (List<User>) query.list();
		return users;
	}

	public List<Rating> getRatings() {
		List<Rating> ratingList = new ArrayList<Rating>();
		SessionFactory sf = getConfiguration().buildSessionFactory();
		Session session = sf.openSession();
		try {
			Query query = session.createQuery("FROM Rating");
			ratingList = query.list();
			if (ratingList != null && !ratingList.isEmpty()) {
				// rating=ratingOperation(rating, ratingList);
			} else {
				LOG.error("Error while fetching rating in Rating");
			}
		} catch (Exception e) {
			LOG.error("Error while fetching user in USER_MASTER", e);
		} finally {
			if (session != null && session.isOpen()) {
			}
		}
		return ratingList;
	}

	private Rating ratingOperation(Rating rating, List ratingList) {
		Iterator userIterator = ratingList.iterator();
		try {
			while (userIterator.hasNext()) {
				rating = (Rating) userIterator.next();
				System.out.println("ratingList:" + rating.getRatingId() + " "
						+ rating.getInput());
				rating.setRatingId(rating.getRatingId());
				rating.setInput(rating.getInput());

				rating.setDescription(rating.getDescription());
				System.out.println("rating values are setted properly");
			}
		} catch (Exception e) {
			LOG.error("error: ", e);
		}
		return rating;
	}

	public List<Quality> getQuality() {
		List<Quality> qualityList = new ArrayList<Quality>();
		SessionFactory sf = getConfiguration().buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery("FROM Quality");
			qualityList = query.list();
			if (qualityList != null && !qualityList.isEmpty()) {
				// rating=ratingOperation(rating, ratingList);
			} else {
				LOG.error("Error while fetching rating in Quality");
			}
		} catch (Exception e) {
			LOG.error("Error while fetching user in USER_MASTER", e);
		} finally {
			if (session != null && session.isOpen()) {
			}
		}
		return qualityList;
	}

	private Quality qualityOperation(Quality quality, List qualityList) {
		Iterator userIterator = qualityList.iterator();
		try {
			while (userIterator.hasNext()) {
				quality = (Quality) userIterator.next();
				System.out.println("qualityList:" + quality.getQualityId()
						+ " " + quality.getDescription() + ""
						+ quality.getTitle());
				quality.setQualityId(quality.getQualityId());
				quality.setDescription(quality.getDescription());
				quality.setTitle(quality.getTitle());
				System.out.println("quality values are setted properly");
			}
		} catch (Exception e) {
			LOG.error("error: ", e);
		}
		return quality;
	}

	public Configuration getConfiguration() {
		return cfg;
	}

	public void setConfiguration(Configuration cfg) {
		this.cfg = cfg;
	}

}
