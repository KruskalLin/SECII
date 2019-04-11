package dataService.messagedataService;

import po.MessagePO;
import util.ResultMessage;
import util.UserCategory;


import java.util.ArrayList;
import java.util.Set;

public interface MessagedataService {
    public int getId();
    public ResultMessage insert(MessagePO messagePO);
    public ArrayList<MessagePO> getAll(UserCategory userCategory);
}
