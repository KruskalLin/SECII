package memberTest;

import dataService.memberdataService.MemberdataService;
import org.junit.Before;
import org.junit.Test;
import po.MemberPO;
import util.MemberCategory;
import util.MemberSearchCondition;
import util.ReceiptState;
import vo.MemberVO;

import java.rmi.Naming;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static ui.util.UserInfomation.url;

public class MemberDataTest {
    private MemberdataService memberdataService;
    @Before
    public void setUp() throws Exception{
        memberdataService =   (MemberdataService) Naming.lookup( url+"MemberData");
    }

    /**
     * memberPO.setMemberName("林");
     memberPO.setDebt(210);
     memberPO.setCredit(310);
     memberPO.setZipCode("000031");
     memberPO.setPhoneNumber("13800000000");
     memberPO.setComment("无");
     memberPO.setVIPgrade(3);
     memberPO.setDebtCeiling(1200);
     memberPO.setEmailAddress("1380212313@aaa.com");
     memberPO.setClerkName("小方");
     memberPO.setAddress("贝克街2号");
     *
     *
     *  memberPO.setMemberName("李晨");
     memberPO.setDebt(210);
     memberPO.setCredit(310);
     memberPO.setZipCode("011031");
     memberPO.setPhoneNumber("15102040000");
     memberPO.setComment("常客");
     memberPO.setVIPgrade(5);
     memberPO.setDebtCeiling(200);
     memberPO.setEmailAddress("1llx212313@aaa.com");
     memberPO.setClerkName("小方");
     memberPO.setAddress("艳阳区小明街34号");


     memberPO.setMemberName("王小儿");
     memberPO.setDebt(210);
     memberPO.setCredit(670);
     memberPO.setZipCode("0031444");
     memberPO.setPhoneNumber("10800882000");
     memberPO.setComment("无");
     memberPO.setVIPgrade(1);
     memberPO.setDebtCeiling(700);
     memberPO.setEmailAddress("wxe1380212313@sina.com");
     memberPO.setClerkName("小可");
     memberPO.setAddress("良心街7号");




     memberPO.setMemberName("温天一");
     memberPO.setDebt(280);
     memberPO.setCredit(110);
     memberPO.setZipCode("120031");
     memberPO.setPhoneNumber("1390155912");
     memberPO.setComment("无");
     memberPO.setVIPgrade(4);
     memberPO.setDebtCeiling(5000);
     memberPO.setEmailAddress("wty1390155912@qq.com");
     memberPO.setClerkName("小可");
     memberPO.setAddress("贝尔街31号");




     memberPO.setMemberName("巴心");
     memberPO.setDebt(0);
     memberPO.setCredit(10);
     memberPO.setZipCode("132031");
     memberPO.setPhoneNumber("1391200000");
     memberPO.setComment("无");
     memberPO.setVIPgrade(2);
     memberPO.setDebtCeiling(1400);
     memberPO.setEmailAddress("bx1391212313@aaa.com");
     memberPO.setClerkName("小奇");
     memberPO.setAddress("思明区思明街223号");



     memberPO.setMemberName("王光亮");
     memberPO.setDebt(999);
     memberPO.setCredit(1920);
     memberPO.setZipCode("114421");
     memberPO.setPhoneNumber("159592133012");
     memberPO.setComment("常客");
     memberPO.setVIPgrade(4);
     memberPO.setDebtCeiling(4300);
     memberPO.setEmailAddress("1390212313@aaa.com");
     memberPO.setClerkName("小心");
     memberPO.setAddress("贝尔街42号");
      *
     */



    @Test
    public void testInAndSee() throws  Exception{
        MemberPO memberPO = memberdataService.getNew();
        int id = memberPO.getMemberId();
        memberPO.setMemberName("lin");
        memberPO.setDebt(12);
        memberPO.setCredit(31);
        memberPO.setZipCode("abc");
        memberPO.setPhoneNumber("1380000");
        memberPO.setComment("this is a da bao jian");
        memberPO.setVIPgrade(3);
        memberPO.setDebtCeiling(1200);
        memberPO.setEmailAddress("13802@aaa.com");
        memberPO.setClerkName("dalao");
        memberPO.setAddress("BeiErJie");
        memberPO.setZipCode("13");
        memberPO.setMemberCategory(MemberCategory.SUPPLIER);
        memberdataService.update(memberPO);
        MemberPO member = memberdataService.showDetail(id);

        assertEquals(member.getCredit(),memberPO.getCredit());



    }

//list.size>2
    @Test
    public void ListCheck() throws  Exception{
        ArrayList<MemberPO> list = memberdataService.search(new MemberSearchCondition());
        int id = 0;
        if(!list.isEmpty()) {
            id = list.get(1).getMemberId();
            memberdataService.delete(list.get(0).getMemberId());
        }
        list = memberdataService.search(new MemberSearchCondition());
        assertEquals(id,list.get(0).getMemberId());
    }


}
