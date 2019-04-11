package mapper;

import po.GoodsClassificationPO;

import java.util.ArrayList;


public interface GoodsClassificationPOMapper {
    public void insert(GoodsClassificationPO po);

    public void delete(String ID);

    public void update(GoodsClassificationPO po);

    public ArrayList<GoodsClassificationPO> show();

    public GoodsClassificationPO getById(String id);
}
