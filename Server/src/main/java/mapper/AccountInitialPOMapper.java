package mapper;

import org.apache.ibatis.annotations.Param;
import po.AccountInitialPO.InitialAccountPO;
import po.AccountInitialPO.InitialGoodsPO;
import po.AccountInitialPO.InitialMemberPO;
import po.AccountPO;
import po.GoodsClassificationPO;
import po.GoodsPO;
import po.MemberPO;

import java.util.ArrayList;

public interface AccountInitialPOMapper {

    void initialGoods(@Param("year")String year);

    void initialAccount(@Param("year")String year);

    void initialMember(@Param("year") String year);

    ArrayList<InitialGoodsPO> showGoods(@Param("year")String year);

    ArrayList<InitialMemberPO> showMember(@Param("year")String year);

    ArrayList<InitialAccountPO> showAccount(@Param("year")String year);

}
