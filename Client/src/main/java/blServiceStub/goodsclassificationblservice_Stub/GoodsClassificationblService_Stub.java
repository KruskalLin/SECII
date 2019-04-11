package blServiceStub.goodsclassificationblservice_Stub;

import blService.goodsClassificationblService.GoodsClassificationblService;
import util.ResultMessage;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.*;

public class GoodsClassificationblService_Stub implements GoodsClassificationblService{
  //  Set<GoodsClassificationVO> set = new TreeSet<>();
    List<GoodsClassificationVO> list = new ArrayList<>();


    public GoodsClassificationblService_Stub() {
        list.add(new GoodsClassificationVO("root",null,null));
        list.add(new GoodsClassificationVO("123","345","root"));
        list.add(new GoodsClassificationVO("124","346","root"));
        list.add(new GoodsClassificationVO("125","347","root"));
        list.add(new GoodsClassificationVO("126","348","root"));
        list.add(new GoodsClassificationVO("127","349","root"));
        list.add(new GoodsClassificationVO("131","light","123"));
        list.add(new GoodsClassificationVO("132","light","123"));
        list.add(new GoodsClassificationVO("133","light","123"));
        list.add(new GoodsClassificationVO("134","light","124"));
        list.add(new GoodsClassificationVO("141","light","131"));

    }

    /**
   	 * 显示所有商品分类
	 * @return
             */
    public List<GoodsClassificationVO> show(){
        System.out.println("show success");
        return list;
    }



    @Override
    public String getID(String fatherId, int order) {
        return null;
    }

    @Override
    public ResultMessage addGoodsClassification(GoodsClassificationVO vo)  {
        return null;
    }

    @Override
    public ResultMessage deleteGoodsClassification(GoodsClassificationVO vo) {
        return null;
    }

    @Override
    public ResultMessage updateGoodsClassification(GoodsClassificationVO vo) {
        return null;
    }

    /**
     * 创建商品分类时调用，根据上层分类编号生成编号
     * @param upID
     * @return
     */
    public String getID(String upID){
        return "123";
    }

    /**
     * 增加分类
     * @param name
     * @param upID
     * @return
     */
    public ResultMessage addGoodsClassification(String name,String upID){
        System.out.println("insert success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 删除商品分类
     * @param ID
     * @return
     */
    public ResultMessage deleteGoodsClassification(String ID){
        System.out.println("delete success");
        return ResultMessage.SUCCESS;
    }

    /**
     * 修改分类
     * @param ID
     * @param name
     * @return
     */
    public ResultMessage updateGoodsClassification(String ID,String name){
        System.out.println("modify success");
        return ResultMessage.SUCCESS;
    }
}
