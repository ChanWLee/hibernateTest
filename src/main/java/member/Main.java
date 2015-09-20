package member;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import util.HibernateUtil;

import java.util.List;

import static member.MemberImpl.*;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> m1 = 
				(List<Integer>) get(Projections.projectionList()
						.add(Projections.max("seq")));
		add("abs" + m1.get(0), "information");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Member.class);
		
		//Restrictions.conjunction() �� and �� �׷��ϰ�, Restrictions.disjunction() �� or �� �׷��մϴ�.
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

		cri.createCriteria("MemberInfo","info",Criteria.LEFT_JOIN);
			
		
		List<Member> m = cri.list();
//		for(Member mm: m)
//			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo());
		
		m.forEach(mm -> 
			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo()) );
//		m.forEach(System.out::println);
		session.getTransaction().commit();
	}
}
