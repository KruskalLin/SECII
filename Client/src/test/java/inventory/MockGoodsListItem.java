package inventory;

public class MockGoodsListItem {
    String goodsName;
    String id;
    int systemNum;
    int factNum;

    public MockGoodsListItem() {
    }

    public MockGoodsListItem(String goodsName, String id, int systemNum, int factNum) {
        this.goodsName = goodsName;
        this.id = id;
        this.systemNum = systemNum;
        this.factNum = factNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getId() {
        return id;
    }

    public int getSystemNum() {
        return systemNum;
    }

    public int getFactNum() {
        return factNum;
    }
}
