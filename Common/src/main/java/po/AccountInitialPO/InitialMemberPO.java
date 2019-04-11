package po.AccountInitialPO;

import util.MemberCategory;

import java.io.Serializable;

public class InitialMemberPO implements Serializable{

    private String year;
    private int memberId;
    private MemberCategory memberCatogory; // 分为进货商和销售商
    private byte[] image;
    private int VIPgrade; // 1~5
    private String memberName;
    private String clerkName;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String emailAddress;
    private int debtCeiling; // 应收额度
    private int debt;
    private int credit;
    private int isDelete;

    public InitialMemberPO(){}

    public InitialMemberPO(String year, int memberId, MemberCategory memberCatogory, byte[] image, int VIPgrade, String memberName, String clerkName, String phoneNumber, String address, String zipCode, String emailAddress, int debtCeiling, int debt, int credit, int isDelete) {
        this.year = year;
        this.memberId = memberId;
        this.memberCatogory = memberCatogory;
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
        this.isDelete = isDelete;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public MemberCategory getMemberCatogory() {
        return memberCatogory;
    }

    public void setMemberCatogory(MemberCategory memberCatogory) {
        this.memberCatogory = memberCatogory;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public int getDebtCeiling() {
        return debtCeiling;
    }

    public void setDebtCeiling(int debtCeiling) {
        this.debtCeiling = debtCeiling;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
