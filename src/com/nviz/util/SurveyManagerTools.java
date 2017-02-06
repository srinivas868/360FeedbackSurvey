package com.nviz.util;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nviz.account.util.PasswordUtil;
import com.nviz.report.util.ReportGeneratorUtils;
import com.nviz.vo.ReportRecordTrail;
import com.nviz.vo.Survey;
import com.nviz.vo.User;

public class SurveyManagerTools {
	
	private static final int GRAPH_HEIGHT = 400;
	private static final int GRAPH_WIDTH = 500;
	private static final String SURVEY_REPORT_FILE_DIR = "E:/Nviz/Nviz_workspace/Nviz360/WebContent/report";
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
	
	/** this method will return Report record trail item
	 * @param userId
	 * @param surveyId
	 * @return
	 * @throws Exception
	 */
	public ReportRecordTrail getReportItem(String userId, String surveyId) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("FROM ReportRecordTrail as r where r.surveyId=:surveyId and r.userId=:userId");
			query.setString("surveyId", surveyId);
			query.setString("userId", userId);
			List reports = query.list();
			if(reports != null && !reports.isEmpty()){
				return (ReportRecordTrail) reports.get(0);
			} else{
				return null;
			}
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			session.close(); 
		}
	}
	
	/** this method will write the data to Database
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
	
	/** this method will generate report for given survey id
	 * @param survey
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void generateReport(Survey survey) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			List<User> usersList = getItems("User");
			for(User user : usersList){
				Query query = session.createQuery("FROM Record as rec where rec.survey=:survey and rec.user=:user");
				query.setParameter("survey", survey);
				query.setParameter("user", user);
				List recordsList = query.list();
				if(recordsList != null && !recordsList.isEmpty()){
					Map map = ReportGeneratorUtils.generateMap(recordsList);
					String filePath = SURVEY_REPORT_FILE_DIR+"//report_"+user.getFirstName()+".pdf";
					ReportGeneratorUtils.writeChartToPDF(map, GRAPH_WIDTH, GRAPH_HEIGHT, filePath);
					addReportRecordItem(survey.getSurveyId(),survey.getTitle(),user.getId(),filePath);
				}
			}
		} catch (Throwable e) {
			throw new Exception(e);
		} finally{
			session.close(); 
		}
	}
	
	/** this method will add report record trail item to Database
	 * @param surveyId
	 * @param surveyTitle
	 * @param userId
	 * @param filePath
	 * @throws Exception
	 */
	public void addReportRecordItem(int surveyId, String surveyTitle, int userId,
			String filePath) throws Exception {
		ReportRecordTrail trail = new ReportRecordTrail();
		trail.setSurveyId(String.valueOf(surveyId));
		trail.setSurveyTitle(surveyTitle);
		trail.setUserId(String.valueOf(userId));
		trail.setReportPath(filePath);
		addItem(trail);
	}

	/** this method will perform the login operations
	 * @param userName
	 * @param password
	 * @throws Exception 
	 */
	public User login(HttpServletRequest request) throws Exception {
		Session session = null;
		try{
			session = getSessionFactory().openSession();
			String login = request.getParameter("login");
			String password = PasswordUtil.encrypt(request.getParameter("password"));
			Query query = session.createQuery("FROM User as u where u.login=:login and u.password=:password");
			query.setString("login", login);
			query.setString(SurveyConstants.PASSWORD, password);
			List users =  query.list();
			if(users != null && !users.isEmpty()){
				return (User) query.list().get(0);
			} else{
				return null;
			}
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