package member;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member {

	@Id
	private int seq;
	@Resource
	private String name;
	@Resource
	private String info;
	//mmappedBy 는 memberInfo 쪽의 Member 변수를 가리키는 변수명
	@OneToMany(mappedBy="member", fetch=FetchType.LAZY)
	private List<MemberInfo> memberInfos;
	
	public Member(String name, String info) {
		super();
		this.name = name;
		this.info = info;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Member(){
	}

	@Override
	public String toString() {
		return "Member [seq=" + seq + ", name=" + name + ", info=" + info + "]";
	}

	public List<MemberInfo> getMemberInfos() {
		return memberInfos;
	}

	public void setMemberInfos(List<MemberInfo> memberInfos) {
		this.memberInfos = memberInfos;
	}

}
