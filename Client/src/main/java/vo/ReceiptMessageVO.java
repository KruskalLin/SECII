package vo;

import blService.billblservice.CashBillReceiptblService;
import blService.billblservice.ChargeBillReceiptblService;
import blService.billblservice.PaymentBillReceiptblService;
import blService.inventoryblService.InventoryDamageReceiptblService;
import blService.inventoryblService.InventoryGiftReceiptblService;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import blService.inventoryblService.InventoryWarningReceiptblService;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import blService.stockblService.StockPurblService;
import blService.stockblService.StockRetblService;
import blService.userblService.UserManagerblService;
import businesslogic.blServiceFactory.MyServiceFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import po.ReceiptMessagePO;
import po.receiptPO.*;
import util.BillType;
import util.MessagePurpose;
import util.UserCategory;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryWarningReceiptVO;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class ReceiptMessageVO {
    private int id;

    private UserVO fromUser;
    private UserVO toUser;
    private UserCategory toUserCategory;

    private MessagePurpose messagePurpose;

    private LocalDateTime createTime;

    private ReceiptVO receiptVO;

    // TODO 这个用switch来写了，没想到别的办法，垃圾垃圾…
    public ReceiptMessageVO(ReceiptMessagePO receiptMessagePO) throws RemoteException {
        try {
            this.id = receiptMessagePO.getId();
            this.toUserCategory = receiptMessagePO.getToUserCategory();
            this.messagePurpose = receiptMessagePO.getMessagePurpose();
            this.createTime = receiptMessagePO.getCreateTime();

            UserManagerblService userbl = MyServiceFactory.getUserManagerblService();
            this.fromUser = userbl.showDetail(receiptMessagePO.getFromUserId());
            if (receiptMessagePO.getToUserId() != 0) { // 可能没有toUser
                this.toUser = userbl.showDetail(receiptMessagePO.getToUserId());
            }

            switch (receiptMessagePO.getBillType()) {
                case InventoryGift:
                    InventoryGiftReceiptPO po0 = new InventoryGiftReceiptPO();
                    po0.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po0.setDayId(receiptMessagePO.getReceiptDayId());
                    InventoryGiftReceiptVO vo0 = new InventoryGiftReceiptVO(po0);
                    receiptVO = ((InventoryGiftReceiptblService) MyblServiceFactory.getService(InventoryGiftReceiptblService.class)).selectByMold(vo0);
                    break;
                case InventoryDamage:
                    InventoryDamageReceiptPO po1 = new InventoryDamageReceiptPO();
                    po1.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po1.setDayId(receiptMessagePO.getReceiptDayId());
                    InventoryDamageReceiptVO vo1 = new InventoryDamageReceiptVO(po1);
                    receiptVO = ((InventoryDamageReceiptblService) MyblServiceFactory.getService(InventoryDamageReceiptblService.class)).selectByMold(vo1);
                    break;
                case InventoryWarning:
                    InventoryWarningReceiptPO po2 = new InventoryWarningReceiptPO();
                    po2.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po2.setDayId(receiptMessagePO.getReceiptDayId());
                    InventoryWarningReceiptVO vo2 = new InventoryWarningReceiptVO(po2);
                    receiptVO = ((InventoryWarningReceiptblService) MyblServiceFactory.getService(InventoryWarningReceiptblService.class)).selectByMold(vo2);
                    break;
                case InventoryOverflow:
                    InventoryOverflowReceiptPO po3 = new InventoryOverflowReceiptPO();
                    po3.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po3.setDayId(receiptMessagePO.getReceiptDayId());
                    InventoryOverflowReceiptVO vo3 = new InventoryOverflowReceiptVO(po3);
                    receiptVO = ((InventoryOverflowReceiptblService) MyblServiceFactory.getService(InventoryOverflowReceiptblService.class)).selectByMold(vo3);
                    break;
                case Cash:
                    CashBillReceiptPO po4 = new CashBillReceiptPO();
                    po4.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po4.setDayId(receiptMessagePO.getReceiptDayId());
                    CashReceiptVO vo4 = new CashReceiptVO(po4);
                    receiptVO = ((CashBillReceiptblService) MyblServiceFactory.getService(CashBillReceiptblService.class)).selectByMold(vo4);
                    break;
                case BillCharge:
                    ChargeBillReceiptPO po5 = new ChargeBillReceiptPO();
                    po5.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po5.setDayId(receiptMessagePO.getReceiptDayId());
                    ChargeReceiptVO vo5 = new ChargeReceiptVO(po5);
                    receiptVO = ((ChargeBillReceiptblService) MyblServiceFactory.getService(ChargeBillReceiptblService.class)).selectByMold(vo5);
                    break;
                case BillPay:
                    PaymentBillReceiptPO po6 = new PaymentBillReceiptPO();
                    po6.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po6.setDayId(receiptMessagePO.getReceiptDayId());
                    PaymentReceiptVO vo6 = new PaymentReceiptVO(po6);
                    receiptVO = ((PaymentBillReceiptblService) MyblServiceFactory.getService(PaymentBillReceiptblService.class)).selectByMold(vo6);
                    break;
                case SalesSell:
                    SalesSellReceiptPO po7 = new SalesSellReceiptPO();
                    po7.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po7.setDayId(receiptMessagePO.getReceiptDayId());
                    SalesSellReceiptVO vo7 = new SalesSellReceiptVO(po7);
                    receiptVO = ((SalesSellblService) MyblServiceFactory.getService(SalesSellblService.class)).selectByMold(vo7);
                    break;
                case SalesRet:
                    SalesRetReceiptPO po8 = new SalesRetReceiptPO();
                    po8.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po8.setDayId(receiptMessagePO.getReceiptDayId());
                    SalesRetReceiptVO vo8 = new SalesRetReceiptVO(po8);
                    receiptVO = ((SalesRetblService) MyblServiceFactory.getService(SalesRetblService.class)).selectByMold(vo8);
                    break;
                case StockPur:
                    StockPurReceiptPO po9 = new StockPurReceiptPO();
                    po9.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po9.setDayId(receiptMessagePO.getReceiptDayId());
                    StockPurReceiptVO vo9 = new StockPurReceiptVO(po9);
                    receiptVO = ((StockPurblService) MyblServiceFactory.getService(StockPurblService.class)).selectByMold(vo9);
                    break;
                case StockRet:
                    StockRetReceiptPO po10 = new StockRetReceiptPO();
                    po10.setCreateTime(receiptMessagePO.getReceiptCreateTime());
                    po10.setDayId(receiptMessagePO.getReceiptDayId());
                    StockRetReceiptVO vo10 = new StockRetReceiptVO(po10);
                    receiptVO = ((StockRetblService) MyblServiceFactory.getService(StockRetblService.class)).selectByMold(vo10);
                    break;
            }
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getPrompt() {
        switch (messagePurpose) {
            case Reject:
                return "您有一份单据被驳回";
            case Submit:
                return "您收到一份新的单据申请";
            case Approve:
                return "您有一份单据被通过";
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptMessageVO)) return false;

        ReceiptMessageVO messageVO = (ReceiptMessageVO) o;

        return id == messageVO.id;
    }

    /**
     * toPO
     */
    public ReceiptMessagePO toPO() {
        ReceiptMessagePO result = new ReceiptMessagePO();
        result.setId(id);
        result.setFromUserId(fromUser.getId());
        result.setToUserId(toUser == null ? 0 : toUser.getId());
        result.setToUserCategory(toUserCategory);

        result.setCreateTime(createTime);
        result.setMessagePurpose(messagePurpose);

        result.setReceiptCreateTime(receiptVO.getCreateTime());
        result.setReceiptDayId(receiptVO.toPO().getDayId());

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserVO getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserVO fromUser) {
        this.fromUser = fromUser;
    }

    public UserVO getToUser() {
        return toUser;
    }

    public void setToUser(UserVO toUser) {
        this.toUser = toUser;
    }

    public UserCategory getToUserCategory() {
        return toUserCategory;
    }

    public void setToUserCategory(UserCategory toUserCategory) {
        this.toUserCategory = toUserCategory;
    }

    public MessagePurpose getMessagePurpose() {
        return messagePurpose;
    }

    public void setMessagePurpose(MessagePurpose messagePurpose) {
        this.messagePurpose = messagePurpose;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public ReceiptVO getReceiptVO() {
        return receiptVO;
    }

    public void setReceiptVO(ReceiptVO receiptVO) {
        this.receiptVO = receiptVO;
    }
}
