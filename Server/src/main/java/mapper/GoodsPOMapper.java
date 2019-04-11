package mapper;

import po.GoodsPO;

import java.util.ArrayList;
import java.util.List;

public interface GoodsPOMapper {
    public void insert(GoodsPO po);

    public void delete(String id);

    public void update(GoodsPO po);

    public ArrayList<GoodsPO> show();

    /**
     * 进行模糊查询的接口
     * */
    public ArrayList<GoodsPO> selectInEffect(String keywords);

    public GoodsPO selectById(String id);
}
