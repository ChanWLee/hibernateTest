package member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Member member = new Member("testname","testInfo");
		
		session.beginTransaction();
		
		Criteria cri = session.createCriteria(Member.class);
		
//		session.save(member);        
		//test
		
		cri.add(Restrictions.like("name", "test"));
		cri.addOrder(Order.desc("seq"));
		List<Member> m = cri.list();
		for(Member mm: m){
			System.out.println(mm.getSeq()+"::"+mm.getName()+"::"+mm.getInfo());
		}
		
		session.getTransaction().commit();
		
		return ;
		
	}
}
