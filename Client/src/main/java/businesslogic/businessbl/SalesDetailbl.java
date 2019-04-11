package businesslogic.businessbl;

import blService.businessblservice.BusinessSearchInfo;
import blService.businessblservice.SalesDetailblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SalesDetailbl implements SalesDetailblService {
    private BusinessSearchInfo<SalesSellReceiptVO> salesSellSearchInfo;

    public SalesDetailbl() throws RemoteException, NotBoundException, MalformedURLException {
        salesSellSearchInfo = MyServiceFactory.getSalesSellSearchInfo();
    }

    @Override
    public ArrayList<ListGoodsItemVO> searchSalesDetail(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return salesSellSearchInfo.search(receiptSearchCondition) // TODO 这个还要filter approved，可能再加下sort按时间也不错
                .stream().filter(r -> r.getReceiptState() == ReceiptState.APPROVED)
                .flatMap(r -> r.getItems().stream())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

