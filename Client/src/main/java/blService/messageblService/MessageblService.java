package blService.messageblService;

import po.MessagePO;
import util.ResultMessage;
import vo.MessageVO;

import java.util.ArrayList;
import java.util.Set;

public interface MessageblService {
    public void send(MessagePO messagePO);
    public void insert(MessagePO messagePO);

}

