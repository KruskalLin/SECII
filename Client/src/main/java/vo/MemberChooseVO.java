package vo;

public class MemberChooseVO {
    private String memberName;
    private int memberId;

    public MemberChooseVO(String memberName, int memberId) {
        this.memberName = memberName;
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return String.format("%05d",memberId)+" "+memberName;
    }
}
