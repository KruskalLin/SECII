package vo;

import javafx.scene.image.Image;
import po.MemberPO;
import util.ImageConvertor;
import util.MemberCategory;

public class MemberVO {
    int memberId;
    MemberCategory memberCategory;
    int degree;
    Image image;
    String name;
    String phone;
    String address;
    String email;
    String zipCode;
    double receiableAmount;
    double give;
    double get;
    String clerkName;
    String comment;
    MemberPO memberPO;

    public MemberVO() {
    }

    public MemberVO(int memberId, MemberCategory memberCategory, int degree, String name, String phone, String address, String email, String zipCode, double receiableAmount, double give, double get, String clerkName, String comment) {
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.degree = degree;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.zipCode = zipCode;
        this.receiableAmount = receiableAmount;
        this.give = give;
        this.get = get;
        this.clerkName = clerkName;
        this.comment = comment;
    }



    public MemberVO(MemberPO memberPO){
        this.memberId = memberPO.getMemberId();
        this.name = memberPO.getMemberName();
        this.zipCode = memberPO.getZipCode();
        this.comment = memberPO.getComment();
        this.image = ImageConvertor.getFXImage(ImageConvertor.getImage(memberPO.getImage()));
        this.address = memberPO.getAddress();
        this.clerkName = memberPO.getClerkName();
        this.degree = memberPO.getVIPgrade();
        this.email = memberPO.getEmailAddress();
        this.get = memberPO.getCredit();
        this.give = memberPO.getDebt();
        this.memberCategory = memberPO.getMemberCategory();
        this.receiableAmount = memberPO.getDebtCeiling();
        this.phone = memberPO.getPhoneNumber();
    }

    public MemberPO toPO(){
        MemberPO memberPO = new MemberPO();
        memberPO.setMemberCategory(memberCategory);
        memberPO.setMemberId(memberId);
        memberPO.setAddress(address);
        memberPO.setIsDeleted(0);
        memberPO.setClerkName(clerkName);
        memberPO.setEmailAddress(email);
        memberPO.setCredit(get);
        memberPO.setDebt(give);
        memberPO.setImage(ImageConvertor.getByte(ImageConvertor.getBuffered(image)));
        memberPO.setDebtCeiling(receiableAmount);
        memberPO.setVIPgrade(degree);
        memberPO.setComment(comment);
        memberPO.setPhoneNumber(phone);
        memberPO.setZipCode(zipCode);
        memberPO.setMemberName(name);
        return memberPO;
    }


    public MemberListVO toListVO(){
        MemberListVO memberListVO = new MemberListVO();
        memberListVO.setClerkName(clerkName);
        memberListVO.setDegree(degree);
        memberListVO.setImage(image);
        memberListVO.setMemberCategory(memberCategory);
        memberListVO.setName(name);
        memberListVO.setMemberId(memberId);
        memberListVO.setMemberVO(this);
        memberListVO.setDebt(give);
        memberListVO.setCredit(get);
        return memberListVO;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getReceiableAmount() {
        return receiableAmount;
    }

    public void setReceiableAmount(double receiableAmount) {
        this.receiableAmount = receiableAmount;
    }

    public double getGive() {
        return give;
    }

    public void setGive(double give) {
        this.give = give;
    }

    public double getGet() {
        return get;
    }

    public void setGet(double get) {
        this.get = get;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
