package com.nviz;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nviz.vo.Address;
import com.nviz.vo.Quality;
import com.nviz.vo.Record;
import com.nviz.vo.Survey;
import com.nviz.vo.User;

public class UserLogin {

	public static void main(String[] args) {
	    Configuration cfg=new Configuration();  
	    cfg.configure("/com/nviz/resource/hibernate.cfg.xml");  
	    SessionFactory sf=cfg.buildSessionFactory();  
	    Session session=sf.openSession();  
	    Transaction tx=session.beginTransaction(); 
	    Query query = session.createQuery("FROM Record"); 
	    List<Survey> list = query.list();
	    Record record=new Record();
	    for(Survey s : list){
	    	record.setSurvey(s);
	    	List<Quality> qts = s.getQualities();
		    for(Quality q : qts){
		    	System.out.println();
		    }
	    }
	   /* Survey updateS = (Survey) session.get(Survey.class,2);
	    Quality quality = new Quality();
	    quality.setTitle("N1");
	    quality.setDescription("N1");
	    updateS.getQuality().add(quality);*/
	    record.setInput(1);
	    session.persist(record);  
	    tx.commit(); 
	    session.close();  
	    System.out.println("success");
	}
}
