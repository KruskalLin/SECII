package businesslogic.memberbl;

import blService.memberblService.MemberblService;
import dataService.memberdataService.MemberdataService;
import javafx.scene.image.Image;
import po.MemberPO;
import util.MemberSearchCondition;
import util.ResultMessage;
import vo.MemberListVO;
import vo.MemberVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static ui.util.UserInfomation.url;

public class Memberbl implements MemberblService{

    private MemberdataService memberdataService;

    public Memberbl() throws RemoteException, MalformedURLException, NotBoundException {
        memberdataService = (MemberdataService) Naming.lookup( url+"MemberData");
    }

    @Override
    public MemberVO getNew() throws RemoteException {
        MemberVO memberVO = new MemberVO(memberdataService.getNew());
        memberVO.setImage(new Image("/default/timg.jpg"));
        return memberVO;
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException {
        return memberdataService.delete(id);
    }

    @Override
    public ResultMessage update(MemberVO memberVO) throws RemoteException {
        return memberdataService.update(memberVO.toPO());
    }

    @Override
    public ArrayList<MemberListVO> search(MemberSearchCondition memberSearchCondition) throws RemoteException {
        return memberdataService.search(memberSearchCondition).stream().map(t->(new MemberVO(t)).toListVO()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public MemberVO showDetail(int id) throws RemoteException {
        System.out.println(id);
        return new MemberVO (memberdataService.showDetail(id));
    }


}
