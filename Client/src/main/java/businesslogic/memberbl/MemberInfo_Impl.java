package businesslogic.memberbl;

import blService.memberblService.MemberInfo;
import blService.memberblService.MemberblService;
import dataService.memberdataService.MemberdataService;
import po.MemberPO;
import util.MemberSearchCondition;
import util.ResultMessage;
import vo.MemberVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static ui.util.UserInfomation.url;

public class MemberInfo_Impl implements MemberInfo {
    private MemberdataService memberdataService;

    public MemberInfo_Impl() throws RemoteException, MalformedURLException, NotBoundException {
        memberdataService = (MemberdataService) Naming.lookup(url + "MemberData");
    }


    @Override
    public ResultMessage updateDebt(int memberId, double debt) throws RemoteException {
        MemberPO memberPO = memberdataService.showDetail(memberId);
        memberPO.setDebt(memberPO.getDebt() + debt);
        memberdataService.update(memberPO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage updateCredit(int memberId, double credit) throws RemoteException {
        MemberPO memberPO = memberdataService.showDetail(memberId);
        memberPO.setCredit(memberPO.getCredit() + credit);
        memberdataService.update(memberPO);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<MemberVO> getALL() throws RemoteException {
        return memberdataService.search(new MemberSearchCondition()).stream().map(t -> new MemberVO(t)).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public MemberVO search(int id) throws RemoteException {
        return new MemberVO(memberdataService.showDetail(id));
    }
}
