package inventory;

import java.util.HashSet;
import java.util.Set;

public class MockGoodsList {
    MockGoodsListItem item;
    Set<MockGoodsListItem> set;

    public MockGoodsList() {
        set = new HashSet<>();
    }

    public MockGoodsList(MockGoodsListItem mockGoodsItem) {
        this.item = mockGoodsItem;
        set = new HashSet<>();
    }

    public MockGoodsListItem getMockGoodsItem() {
        return new MockGoodsListItem();
    }

    public void addGoodsItem(MockGoodsListItem item){
        set.add(item);
    }

    public int getTotalCount(){return 0;}



}
