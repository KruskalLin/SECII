package vo;

import util.MemberCategory;

import java.util.HashSet;

public class MemberSearchVO {
    HashSet<MemberCategory> memberCategories;

    public MemberSearchVO(){ this.memberCategories = new HashSet<>();}

    public HashSet<MemberCategory> getMemberCategories() {
        return memberCategories;
    }

    public void setMemberCategories(HashSet<MemberCategory> memberCategories) {
        this.memberCategories = memberCategories;
    }
}
