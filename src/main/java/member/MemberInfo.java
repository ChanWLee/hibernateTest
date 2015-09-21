package member;

import javax.persistence.*;

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
	
	

	/**
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}
	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}
	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}
	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}
	/**
	 * @return the memberSeq
	 */
	public int getMemberSeq() {
		return MemberSeq;
	}
	/**
	 * @param memberSeq the memberSeq to set
	 */
	public void setMemberSeq(int memberSeq) {
		MemberSeq = memberSeq;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MemberInfo [seq=" + seq + ", MemberSeq=" + MemberSeq + ", member=" + member + ", info=" + info
				+ ", etc=" + etc + "]";
	}
	
}
