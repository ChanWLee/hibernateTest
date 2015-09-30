package member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="member_info")
public class MemberInfo {

	@Id
	private int seq;
	@Column(name="member_seq")
	private int MemberSeq;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member_seq", insertable=false, updatable=false)
	private Member member;
	
	private String info;
	private String etc;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getMemberSeq() {
		return MemberSeq;
	}
	public void setMemberSeq(int memberSeq) {
		MemberSeq = memberSeq;
	}
	@Override
	public String toString() {
		return "MemberInfo [seq=" + seq + ", MemberSeq=" + MemberSeq + ", member=" + member + ", info=" + info
				+ ", etc=" + etc + "]";
	}
	
}
