package mapper;

import po.MemberPO;
import po.UserPO;
import util.MemberCategory;
import util.MemberSearchCondition;
import util.UserSearchCondition;

import java.lang.reflect.Member;
import java.util.ArrayList;

public interface MemberPOMapper {


    public int getId();
    public void insert(MemberPO memberPO);
    public void delete(int id);
    public void update(MemberPO memberPO);
    public MemberPO showDetail(int id);
    public ArrayList<MemberPO> search(MemberSearchCondition memberSearchCondition);
}
