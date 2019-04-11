package blServiceStub.accountblservice_Stub;

import blService.accountblService.AccountblService;
import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.util.ArrayList;
import java.util.List;

public class AccountblService_Stub /*implements AccountblService*/{

    List<AccountListVO> list = new ArrayList<>();
    public AccountblService_Stub(){
        list.add(new AccountListVO(1000,"sb",1000));
        list.add(new AccountListVO(2000,"sb",2000));
        list.add(new AccountListVO(3000,"sb",3000));
        list.add(new AccountListVO(4000,"sb",4000));
        list.add(new AccountListVO(5000,"sb",5000));
        list.add(new AccountListVO(6000,"sb",6000));
        list.add(new AccountListVO(7000,"sb",7000));
        list.add(new AccountListVO(8000,"sb",8000));
        list.add(new AccountListVO(9000,"sb",9000));
        list.add(new AccountListVO(1001,"sb",1001));
    }


    public List<AccountListVO> showAllAccounts(){
        return list;
    }

    public List<AccountListVO> search(String keyword){
        return null;
    }

    public ResultMessage add(AccountListVO accountListVO){
        list.add(accountListVO);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage delete(int ID){
        AccountListVO temp = new AccountListVO();
        for(AccountListVO accountListVO:list){
            if(accountListVO.getID()==ID){
                temp=accountListVO;
                break;
            }
        }
        list.remove(temp);
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(AccountVO accountVO){return  ResultMessage.SUCCESS;}
}
