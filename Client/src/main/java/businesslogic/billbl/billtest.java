package businesslogic.billbl;

import po.receiptPO.PaymentBillReceiptPO;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import vo.billReceiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class billtest {

    public static void main(String[] args)throws RemoteException,NotBoundException,MalformedURLException{

        /*PaymentBillReceiptbl p = new PaymentBillReceiptbl();
        for(int i=0;i<10;i++){
            PaymentReceiptVO vo = p.getNew();
            ArrayList<TransferItemVO> temp = new ArrayList<>();
            temp.add(new TransferItemVO(1,1,"1"));
            temp.add(new TransferItemVO(2,2,"2"));
            vo.setTransferList(temp);
            p.update(vo);
        }*/

        ChargeBillReceiptbl p = new ChargeBillReceiptbl();
        /*for(int i=0;i<10;i++){
            ChargeReceiptVO vo= p.getNew();
            ArrayList<TransferItemVO> temp = new ArrayList<>();
            temp.add(new TransferItemVO(1,1,"1"));
            temp.add(new TransferItemVO(2,2,"2"));
            vo.setTransferList(temp);
            vo.setReceiptState(ReceiptState.PENDING);
            p.update(vo);
        }*/
        ArrayList<ChargeReceiptVO> list = p.search(new RespectiveReceiptSearchCondition());
        System.out.println(list.size());
        System.out.println(list.get(0).getClientID());





    }
}
