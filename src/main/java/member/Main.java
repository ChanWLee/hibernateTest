package member;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import util.HibernateUtil;

import java.util.List;

public class Main {

	@SuppressWarnings({ "unchecked" })
	public static void main(String[] args) {
//		List<Integer> m1 = (List<Integer>) get(Projections.projectionList().add(Projections.max("seq")));
//		add("abs" + m1.get(0), "information");
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria cri = session.createCriteria(Member.class);
//		session.savseOrUpdate(arg0);
		
		cri.createAlias("memberInfos","m",JoinType.LEFT_OUTER_JOIN);
//		cri.createAlias("member", "m").add(Restrictions.like("m.name","abs",MatchMode.ANYWHERE));

//		cri.createCriteria("MemberInfo","info",JoinType.LEFT_OUTER_JOIN)
//				.add(Restrictions.eq("name", "abs"));


		List<Member> mi = cri.list();
		
//		mi.forEach(System.out::println);

		for(Member mm: mi)
			System.out.println(mm.getSeq()+"\t"+mm.getName()+"  \t"+mm.getInfo()+"  \t"+mm.getMemberInfos());
		
//		mi.forEach(mm -> 
//			System.out.println(mm.getSeq()+"\t"+mm.getInfo()+"\tmember: "+mm.getMember()..getName()+"  \t"+mm.getMember().getInfo()) );

		session.getTransaction().commit();
		//group_concat test
//		
//		session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		SQLQuery qr = session.createSQLQuery("select m.seq as seq, m.name as name, group_concat(mi.info) as info from member m right join member_info mi on mi.member_seq = m.seq group by m.seq");
//		qr.setResultTransformer(Transformers.aliasToBean(Member.class));
//
//		List<Member> li = qr.list();
//		session.getTransaction().commit();
//		li.forEach(System.out::println);

		
	}
}
