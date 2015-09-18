package member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;

import util.HibernateUtil;

public class MemberImpl {

	/**
	 * @param name
	 * @param info
	 */
	public static void add(String name, String info){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Member member = new Member(name,info);
		session.beginTransaction();
		session.save(member);     
		session.getTransaction().commit();
	}
	
	/**
	 * @param ProjectionList
	 * @return List
	 */
	public static List<?> get(ProjectionList proj){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Member.class);
		crit.setProjection(proj);
		List<?> list = crit.list();
		session.getTransaction().commit();
		return list;
	}
	

}
