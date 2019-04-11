package po;

import util.MemberCategory;

import java.io.Serializable;

public class MemberPO implements Serializable{
    private int memberId;
    private MemberCategory memberCategory; // 分为进货商和销售商
    private int VIPgrade; // 1~5
    private byte[] image;
    private String memberName;
    private String clerkName;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String emailAddress;
    private double debtCeiling; // 应收额度
    private double debt;
    private double credit;
    private int isDeleted;
    private String comment;
    public MemberPO() {
    }


    public MemberPO(int memberId, MemberCategory memberCategory,byte[] image,int VIPgrade, String memberName, String clerkName, String phoneNumber, String address, String zipCode, String emailAddress, double debtCeiling, double debt, double credit, String comment) {
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.image = image;
        this.VIPgrade = VIPgrade;
        this.memberName = memberName;
        this.clerkName = clerkName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
        this.debtCeiling = debtCeiling;
        this.debt = debt;
        this.credit = credit;
        this.comment = comment;
    }

    public MemberPO(int memberId, MemberCategory memberCategory,byte[] image,int VIPgrade, String memberName, String clerkName, String phoneNumber, String address, String zipCode, String emailAddress, double debtCeiling, double debt, double credit, int isDeleted, String comment) {
        this.memberId = memberId;
        this.memberCategory = memberCategory;
        this.image = image;
        this.VIPgrade = VIPgrade;
        this.memberName = memberName;
        this.clerkName = clerkName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.emailAddress = emailAddress;
        this.debtCeiling = debtCeiling;
        this.debt = debt;
        this.credit = credit;
        this.isDeleted = isDeleted;
        this.comment = comment;
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

    public int getVIPgrade() {
        return VIPgrade;
    }

    public void setVIPgrade(int VIPgrade) {
        this.VIPgrade = VIPgrade;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getDebtCeiling() {
        return debtCeiling;
    }

    public void setDebtCeiling(double debtCeiling) {
        this.debtCeiling = debtCeiling;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
