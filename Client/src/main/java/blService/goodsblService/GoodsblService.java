package blService.goodsblService;

import util.ResultMessage;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface GoodsblService {
    /**
     * 显示所有商品
     */
    public Set<GoodsVO> show() throws RemoteException;

    /**
     * 增加商品
     * @param goodsVO
     */
    public ResultMessage addGoods(GoodsVO goodsVO) throws RemoteException;

    /**
     * 删除商品
     * @param goodsVO
     * @return
     */
    public ResultMessage deleteGoods(GoodsVO goodsVO) throws RemoteException;

    /**
     * 更改商品属性
     * @param goodsVO
     * @return
     */
    public ResultMessage updateGoods(GoodsVO goodsVO) throws RemoteException;

    /**
     * 根据商品编号或名称查找商品
     * @param info
     * @return
     */
    public List<GoodsVO> searchGoods(String info) throws RemoteException;

    /**
     * 根据分类编号和添加次序生成编号
     * @param upID
     * @return
     */
    public String getID(String upID ,int order) throws RemoteException;

    public GoodsVO showDetail(String id) throws RemoteException;
}
