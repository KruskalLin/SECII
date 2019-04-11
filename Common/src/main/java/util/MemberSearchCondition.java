package util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberSearchCondition implements Serializable{

    int degree;
    private MemberCategory memberCategory;

    public MemberSearchCondition() {
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public MemberCategory getMemberCategory() {
        return memberCategory;
    }

    public void setMemberCategory(MemberCategory memberCategory) {
        this.memberCategory = memberCategory;
    }
}
