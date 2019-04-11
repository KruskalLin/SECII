/*
package goods;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MockGoodsList {
    MockGoodsItem mockGoodsItem;
    Set<MockGoodsItem> set;

    public MockGoodsList() {
        set = new HashSet<>();
    }

    public MockGoodsList(MockGoodsItem mockGoodsItem) {
        this.mockGoodsItem = mockGoodsItem;
        set = new HashSet<>();
    }

    public MockGoodsItem getMockGoodsItem() {
        return mockGoodsItem;
    }

    public void addGoodsItem(MockGoodsItem item){
        set.add(item);
    }

    public int getTotalCount(){return 0;}

    public void print(){
        Iterator<MockGoodsItem> it = set.iterator();
        while(it.hasNext()){
            MockGoodsItem item = it.next();
            System.out.println(item.id+" "+item.factCount);
        }
    }
}
*/
