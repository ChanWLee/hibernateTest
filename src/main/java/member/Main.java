package member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import util.HibernateUtil;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> m1 = 
				(List<Integer>) MemberImpl.get( Projections.projectionList()
						.add(Projections.max("seq")) );
		MemberImpl.add("abs"+m1.get(0),"information");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Member.class);
		
		cri.add(Restrictions.like("info", "inf", MatchMode.START)) //'inf%'
			.add(Property.forName("name").in(new String[]{"test","abs"}))
			.addOrder(Order.asc("seq"));
//		cri.addOrder(Property.forName("seq").asc());
		List<Member> m = cri.list();
//		for(Member mm: m){
//			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo());
//	}
		m.forEach(mm -> 
			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo()) );
		session.getTransaction().commit();
		
		return ;
		
	}
}
