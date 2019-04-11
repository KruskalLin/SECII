package inventory;

public class MockInventoryDamage {
    String id;
    MockGoodsList goodsList;
    String userName;

    public MockInventoryDamage(String id, MockGoodsList goodsList, String userName) {
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
