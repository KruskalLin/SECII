package blService.goodsClassificationblService;

import com.sun.org.apache.regexp.internal.RE;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface GoodsClassificationblService {
    /**
     * 显示所有商品分类
     *
     * @return
     */
    public List<GoodsClassificationVO> show() throws RemoteException;

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param fatherId
     * @param order
     * @return
     */
    public String getID(String fatherId, int order) throws RemoteException;

    /**
     * 增加分类
     * @param vo
     * @return
     */
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo) throws RemoteException;

    /**
     * 删除商品分类
     * @param vo
     * @return
     */
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) throws RemoteException;

    /**
     * 修改分类
     * @param vo
     * @return
     */
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) throws RemoteException;
}
