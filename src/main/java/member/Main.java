package member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import util.HibernateUtil;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> m1 = 
				(List<Integer>) MemberImpl.get(Projections.projectionList()
							.add(Projections.max("seq")) );
		MemberImpl.add("abs"+m1.get(0),"information");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Member.class);
		
		//Restrictions.conjunction() 은 and 로 그룹하고, Restrictions.disjunction() 은 or 로 그룹합니다.
		cri
		/*
			.add(Restrictions.or(
						Restrictions.or(
								Restrictions.like("info", "inf", MatchMode.START)
								,Restrictions.like("name", "ab", MatchMode.ANYWHERE)
						)
						,Property.forName("name").in(new String[]{"test","abs"})
						,Restrictions.between("seq", 1, 20)
						)
					)
			*/
			/*.add(Restrictions.conjunction()//( X and X ) <-> disjunction() ( X or X ) 
					.add(Restrictions.disjunction()
							.add(Restrictions.like("info", "inf", MatchMode.START))
							.add(Restrictions.like("name", "ab", MatchMode.ANYWHERE))
					)
					.add(Property.forName("name").in(new String[]{"test","abs"}))
					.add(Restrictions.between("seq", 1, 20))
			)*/
//			.addOrder(Order.asc("seq"))
//		cri.addOrder(Property.forName("seq").asc());
			.setProjection(Projections.projectionList()
					.add(Projections.id().as("seq"))
					.add(Projections.property("name").as("name"))
					.add(Projections.groupProperty("info").as("info")))
			.setResultTransformer(Transformers.aliasToBean(Member.class))
			;
			
		
		List<Member> m = cri.list();
//		for(Member mm: m)
//			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo());
		
		m.forEach(mm -> 
			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo()) );
//		m.forEach(System.out::println);
		session.getTransaction().commit();
		
		return ;
		
	}
}
