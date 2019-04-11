package vo.receiptVO;

import blService.memberblService.MemberInfo;
import businesslogic.memberbl.MemberInfo_Impl;
import po.ReceiptGoodsItemPO;
import po.receiptPO.StockReceiptPO;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.MemberVO;

import java.lang.reflect.Member;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StockReceiptVO extends ReceiptVO {

    protected int memberId;
    protected String memberName;
    protected String stockName;
    protected double sum;
    protected ArrayList<ListGoodsItemVO> items = new ArrayList<>();
    protected String comment;

    public StockReceiptVO() {
    }
    public StockReceiptVO(StockReceiptPO stockReceiptPO) throws RemoteException, NotBoundException, MalformedURLException {
        super(stockReceiptPO);
        if(stockReceiptPO.getMemberId()!=0) {
            MemberInfo memberInfo = new MemberInfo_Impl();
            MemberVO memberVO = memberInfo.search(stockReceiptPO.getMemberId());
            this.memberName = memberVO.getName();
        }
        this.memberId = stockReceiptPO.getMemberId();
        this.stockName =stockReceiptPO.getStockName();
        this.sum =stockReceiptPO.getOriginSum();
        this.items = stockReceiptPO.getGoodsList() == null ? new ArrayList<>()
                : Arrays.stream(stockReceiptPO.getGoodsList()).map(p -> new ListGoodsItemVO(p, getCreateTime().toLocalDate())).collect(Collectors.toCollection(ArrayList::new));
        this.comment = stockReceiptPO.getComment();
    }

    public StockReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int memberId, String stockName, double sum, ArrayList<ListGoodsItemVO> items, String comment) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.memberId = memberId;
        this.stockName = stockName;
        this.sum = sum;
        this.items = items;
        this.comment = comment;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public ArrayList<ListGoodsItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<ListGoodsItemVO> items) {
        this.items = items;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public <T extends StockReceiptPO> T toStockReceiptPO(Class<T> receiptClass) {
        T result = toReceiptPO(receiptClass);
        result.setMemberId(memberId);
        result.setStockName(stockName);
        result.setOriginSum(sum);
        result.setGoodsList(items == null ? null : items.stream().map(ListGoodsItemVO::toPo).toArray(ReceiptGoodsItemPO[]::new));
        result.setComment(comment);
        return result;
    }
}
