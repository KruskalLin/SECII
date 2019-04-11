package vo;

import javafx.scene.image.Image;
import util.MemberCategory;
import vo.abstractVO.SelectableVO;

public class MemberListVO extends SelectableVO<MemberListVO>{
    private int degree;
    private String name;
    private Image image;
    private int memberId;
    private MemberCategory memberCategory;
    private String clerkName;
    private double debt;
    private double credit;
    boolean multiple = true;
    MemberVO memberVO;

    public MemberListVO() {
    }

    public MemberVO toVO(){
        return this.memberVO;
    }
    public MemberListVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    public MemberVO getMemberVO() {
        return memberVO;
    }

    public void setMemberVO(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MemberCategory getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(MemberCategory memberCategory) {
        this.memberCategory = memberCategory;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }


    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
