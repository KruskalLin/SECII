package vo.inventoryVO;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;
import java.util.Set;

public class InventoryViewVO{
    /** 开始日期 */
    private String beginDate;
    /** 结束日期 */
    private String endDate;
    /* 库存总数*/
    private int totalNum;

    private Set<InventoryViewItemVO> viewList;

    public InventoryViewVO() {
    }

    public InventoryViewVO(String beginDate, String endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public InventoryViewVO(String beginDate, String endDate, int totalNum, Set<InventoryViewItemVO> viewList) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.totalNum = totalNum;
        this.viewList = viewList;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public Set<InventoryViewItemVO> getViewList() {
        return viewList;
    }

    public void setViewList(Set<InventoryViewItemVO> viewList) {
        this.viewList = viewList;
    }

}
