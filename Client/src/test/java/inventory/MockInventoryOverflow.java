package inventory;

public class MockInventoryOverflow {
    String id;
    MockGoodsList goodsList;
    String userName;

    public MockInventoryOverflow(String id, MockGoodsList goodsList, String userName) {
        this.id = id;
        this.goodsList = goodsList;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

}
