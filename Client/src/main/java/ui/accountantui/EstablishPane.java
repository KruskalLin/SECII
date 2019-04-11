package ui.accountantui;

import blService.initialblservice.InitialblService;
import businesslogic.initialbl.Initialbl;
import businesslogic.logbl.Logbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jxl.write.WriteException;
import org.controlsfx.control.textfield.CustomTextField;
import po.AccountPO;
import po.GoodsPO;
import po.MemberPO;
import ui.common.bigPane.GatePane;
import ui.common.mixer.ExcelExportableMixer;
import ui.util.*;
import util.EventCategory;
import vo.AccountVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsVO;

import java.io.File;
import java.lang.reflect.Member;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class EstablishPane extends GatePane implements ExcelExportableMixer{

    //三个ring，要设置maxnum也就是最大数，和当前的num，就会显示进度了
    LabelRingIndicator members = new LabelRingIndicator();
    LabelRingIndicator accounts = new LabelRingIndicator();
    LabelRingIndicator goods = new LabelRingIndicator();




    @FXML
    AnchorPane ringPane;
    @FXML
    Label maxNum;


    @FXML
    Label date;
    @FXML
    Label user;
    @FXML
    CustomTextField file;  //这个是存储的绝对路径field


    @FXML
    JFXButton establish;


    @FXML
    JFXListView<MemberPO> memberList;
    @FXML
    JFXListView<GoodsPO> goodsList;
    @FXML
    JFXListView<AccountPO> accountList;

    private Logbl logbl;


    ObservableList<MemberPO> memberPOs = FXCollections.observableArrayList();
    ObservableList<AccountPO> accountPOs = FXCollections.observableArrayList();
    ObservableList<GoodsPO> goodsPOs = FXCollections.observableArrayList();


    public EstablishPane(){
        super();

        //这些坐标别管
        members.setTranslateX(-20);
        members.setTranslateY(50);
        members.setTitle("客户数量");
        accounts.setTranslateX(90);
        accounts.setTranslateY(50);
        accounts.setTitle("商品数量");
        accounts.getStylesheets().add("/css/circleprogress2.css");
        goods.setTranslateX(200);
        goods.setTranslateY(50);
        goods.setTitle("账户数量");
        goods.getStylesheets().add("/css/circleprogress3.css");
        ringPane.getChildren().addAll(members,accounts,goods);
        date.setText(LocalDate.now().toString());
        user.setText(UserInfomation.username);

        try{
            logbl = new Logbl();
        }catch (Exception e){
            e.printStackTrace();
        }


        //测试一下可不可用
        setMaxNum(400);
        try{
            InitialblService initialblService = new Initialbl();
            ArrayList<MemberPO> list1 = initialblService.showMember("2018");
            members.setNum(list1.size());
            ArrayList<GoodsPO> list2 = initialblService.showGoods("2018");
            goods.setNum(list2.size());
            ArrayList<AccountPO> list3 = initialblService.showAccount("2018");
            accounts.setNum(list3.size());
            for(MemberPO po:list1){
                memberPOs.add(po);
            }
            for(GoodsPO po:list2){
                goodsPOs.add(po);
            }
            for(AccountPO po:list3){
                accountPOs.add(po);
            }
        }catch(Exception e){
            e.printStackTrace();
        }



        memberList.setCellFactory(new Callback<ListView<MemberPO>, ListCell<MemberPO>>() {
            @Override
            public ListCell<MemberPO> call(ListView<MemberPO> param) {
                return new JFXListCell<MemberPO>(){
                    @Override
                    public void updateItem(MemberPO item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                        }else{
                            ListHBox listHBox = new ListHBox();
                            listHBox.setLabelId(item.getMemberId()+"");
                            listHBox.setName(item.getMemberName());
                            setGraphic(listHBox);
                        }
                    }
                };
            }
        });
        memberList.setItems(memberPOs);

        goodsList.setCellFactory(new Callback<ListView<GoodsPO>, ListCell<GoodsPO>>() {
            @Override
            public ListCell<GoodsPO> call(ListView<GoodsPO> param) {
                return new JFXListCell<GoodsPO>(){
                    @Override
                    public void updateItem(GoodsPO item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                        }else{
                            ListHBox listHBox = new ListHBox();
                            listHBox.setLabelId(item.getId());
                            listHBox.setName(item.getGoodName());
                            setGraphic(listHBox);
                        }
                    }
                };
            }
        });
        goodsList.setItems(goodsPOs);


        accountList.setCellFactory(new Callback<ListView<AccountPO>, ListCell<AccountPO>>() {
            @Override
            public ListCell<AccountPO> call(ListView<AccountPO> param) {
                return new JFXListCell<AccountPO>(){
                    @Override
                    public void updateItem(AccountPO item,boolean empty){
                        super.updateItem(item,empty);
                        if(empty){
                            setGraphic(null);
                        }else{
                            ListHBox listHBox = new ListHBox();
                            listHBox.setLabelId(item.getID()+"");
                            listHBox.setName(item.getName()+"   "+item.getBalance());
                            setGraphic(listHBox);
                        }
                    }
                };
            }
        });
        accountList.setItems(accountPOs);

    }

    @Override
    public String getExcelName(){
        return "期初建账";
    }

    @Override
    public void writeSheet()throws WriteException{
        myAddCell(0, 0, "客户编号");
        myAddCell(1, 0, "客户名称");
        myAddCell(2, 0, "联系方式");
        myAddCell(3,0,"应收");
        myAddCell(4,0,"应付");
        for(int i=0;i<memberPOs.size();i++){
            MemberPO po = memberPOs.get(i);
            myAddCell(0,i+1,po.getMemberId());
            myAddCell(1,i+1,po.getMemberName());
            myAddCell(2,i+1,po.getPhoneNumber());
            myAddCell(3,i+1,po.getDebt());
            myAddCell(4,i+1,po.getCredit());
        }

        myAddCell(6,0,"商品编号");
        myAddCell(7,0,"商品名称");
        myAddCell(8,0,"最近进价");
        myAddCell(9,0,"最近售价");
        for(int i=0;i<goodsPOs.size();i++){
            GoodsPO po = goodsPOs.get(i);
            myAddCell(6,i+1,po.getId());
            myAddCell(7,i+1,po.getGoodName());
            myAddCell(8,i+1,po.getRecentPurPrice());
            myAddCell(9,i+1,po.getRecentSalePrice());
        }

        myAddCell(11,0,"账户编号");
        myAddCell(12,0,"账户名称");
        myAddCell(13,0,"账户余额");
        for(int i=0;i<accountPOs.size();i++){
            AccountPO po = accountPOs.get(i);
            System.out.println(po.getID()+po.getName()+po.getBalance());
            myAddCell(11,i+1,po.getID());
            myAddCell(12,i+1,po.getName());
            myAddCell(13,i+1,po.getBalance());
        }

    }



    @Override
    protected void refreshAfterMath() {

    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {

    }

    @Override
    protected void updateDataFromBl() throws RemoteException {

    }


    //ring设置
    public void setMaxNum(int num){
        members.setMax(num);
        accounts.setMax(num);
        goods.setMax(num);
        this.maxNum.setText(num+"");
    }
    //ring设置
    public void setMemberNum(int num){
        this.members.setNum(num);
    }
    //ring设置
    public void setAccountNum(int num){
        this.accounts.setNum(num);
    }
    //ring设置
    public void setGoodsNum(int num){
        this.goods.setNum(num);
    }


    @FXML
    public void chooseFile(){
        exportExcelMixer();
        /*Stage fileStage = null;
        DirectoryChooser folderChooser = new DirectoryChooser();
        folderChooser.setTitle("选择文件夹");
        File selectedFile = folderChooser.showDialog(fileStage);
        this.file.setText(selectedFile.getAbsolutePath());*/
    }
    //button的响应
    @FXML
    public void establish(){
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        try{
            InitialblService initialblService = new Initialbl();
            ArrayList<AccountPO> list = new ArrayList<>();
            list = initialblService.showAccount(year);
            if(list.size()!=0){
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","本年度已期初建账，无法重复操作","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
            }else{
                initialblService.initial(year);
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","操作成功","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
                try{
                    logbl.insertString(EventCategory.InitialAccount,"日期为"+date.getText());
                }catch (Exception e){
                    e.printStackTrace();
                }
                new EstablishPane().refresh(true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected String getURL() {
        return "/salesui/establishPane.fxml";
    }


}
