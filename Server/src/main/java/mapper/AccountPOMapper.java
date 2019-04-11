package mapper;

import po.AccountPO;

import java.util.ArrayList;
import java.util.List;

public interface AccountPOMapper {

    int getID(String name);

    AccountPO getAccountByName(String name);

    void insert(AccountPO accountPO);

    void update(AccountPO accountPO);

    void delete(Integer ID);

    ArrayList<AccountPO> selectInEffect(String keyword);

    ArrayList<AccountPO> getAll();
}
