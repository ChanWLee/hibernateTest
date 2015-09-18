package member;

import javax.transaction.Transactional;

import org.hibernate.Session;

import util.HibernateUtil;

public class MemberImpl {

	@Transactional
	public static void add(String name, String info){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Member member = new Member(name,info);
		session.save(member);        
	}
	
}
