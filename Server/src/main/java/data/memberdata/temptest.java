package data.memberdata;

import po.MemberPO;

public class temptest {
    public static void main(String[] args){
        try{
            MemberData memberData = new MemberData();
            MemberPO m = memberData.getNew();
            m.setClerkName("123");
            memberData.update(m);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
