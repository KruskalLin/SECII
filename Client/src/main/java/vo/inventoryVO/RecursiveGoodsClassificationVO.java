package vo.inventoryVO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import po.GoodsClassificationPO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RecursiveGoodsClassificationVO {
    private String id;
    private String name;
    private RecursiveGoodsClassificationVO father;
    private List<RecursiveGoodsClassificationVO> children; // 如果是第一次建的空类，两个都是null的？
    private List<GoodsVO> goods;
    // TODO 其实我想能不能通过面向对象把这两个解决掉的。只有一个…但是乍一想似乎还是很多逻辑判断。
    // TODO 另外，是否应该所有的array和list都宁愿是空的，也不要是null。这是个好习惯？

    public boolean mark = false;
    // 这个只是为了写搜索的时候写的方便…唉…这种怎么才能把algorithms里的东西用上来…


    public RecursiveGoodsClassificationVO() {
    }

    // 这是一个做了一半的方法，不太确定是否应该这么写，因为剩下的转换职则交给MyGoodsClassificationbl了
    public RecursiveGoodsClassificationVO(GoodsClassificationPO goodsClassificationPO) {
        id = goodsClassificationPO.getId();
        name = goodsClassificationPO.getName();
        children = new ArrayList<>();
        goods = new ArrayList<>();
    }

    public GoodsClassificationPO toPO() {
        GoodsClassificationPO result = new GoodsClassificationPO();

        result.setId(id);
        result.set_name(name);
        result.setFatherId(father == null ? null : father.getId());
        result.setGoodsId(goods == null || goods.isEmpty() ? null : goods.stream().map(GoodsVO::getId).toArray(String[]::new));
        result.setChildrenId(children == null || children.isEmpty() ? null : children.stream().map(RecursiveGoodsClassificationVO::getId).toArray(String[]::new));

        return result;
    }

    // 这些逻辑有些越俎代庖，但是我感觉这是很自然的写法呃
    // 这里的新数量采用的是递增式的而不是补漏式的。应该没什么大问题吧
    public String getNextChildrenId() {
        IntegerProperty predecessorMaxId = new SimpleIntegerProperty(-1);
        getChildren().stream()
                .mapToInt(gvo -> Integer.parseInt(gvo.getId().substring(1 + gvo.getId().lastIndexOf("/"))))
                .max().ifPresent(predecessorMaxId::set);
        return getId() + "/" + (predecessorMaxId.get() + 1);
    }

    // 这两个可以提一个函数式的pattern出来，但是要传两个方法，比较麻烦，所以就不管了。
    public String getNextGoodsId() {
        IntegerProperty predecessorMaxId = new SimpleIntegerProperty(-1);
        getGoods().stream()
                .mapToInt(gvo -> Integer.parseInt(gvo.getId().substring(1 + gvo.getId().lastIndexOf("/"))))
                .max().ifPresent(predecessorMaxId::set);
        return getId() + "/" + (predecessorMaxId.get() + 1);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecursiveGoodsClassificationVO getFather() {
        return father;
    }

    public void setFather(RecursiveGoodsClassificationVO father) {
        this.father = father;
    }

    public List<RecursiveGoodsClassificationVO> getChildren() {
        return children;
    }

    public void setChildren(List<RecursiveGoodsClassificationVO> children) {
        this.children = children;
    }

    public List<GoodsVO> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsVO> goods) {
        this.goods = goods;
    }
}
