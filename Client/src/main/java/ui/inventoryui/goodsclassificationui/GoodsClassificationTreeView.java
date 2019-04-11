package ui.inventoryui.goodsclassificationui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckTreeView;
import ui.common.BoardController;
import ui.inventoryui.goodsui.GoodDetailPane;
import ui.util.DoubleButtonDialog;
import ui.util.PaneFactory;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class GoodsClassificationTreeView extends CheckTreeView<GoodsClassificationVO> {
    private BoardController myBoardController;
    private StackPane mainpane;
    private BorderPane borderPane;
    private GoodsClassificationblService goodsClassificationblService;
    private List<GoodsClassificationVO> classificationList;
    private GoodDetailPane goodDetailPane;
    private JFXButton addGood = new JFXButton("增加商品");
    private JFXButton add = new JFXButton("增加");
    private JFXButton delete = new JFXButton("删除");
    private JFXButton modify = new JFXButton("修改");
    private Goodsbl goodsbl = new Goodsbl();

    private final Node rootIcon =
            new ImageView(new Image(getClass().getResourceAsStream("/default/goodsClassification.png")));

    private CheckBoxTreeItem<GoodsClassificationVO> root = new CheckBoxTreeItem<>();

    public GoodsClassificationTreeView() throws RemoteException, NotBoundException, MalformedURLException {
        super();
        root.setExpanded(true);
        root.setSelected(true);
        root.setIndependent(true);

    }

    public Node getPane() throws RemoteException {
        root.setIndependent(true);
        root.setGraphic(rootIcon);
        root = buildTree();
        root.setExpanded(true);
        this.setRoot(root);
        this.setEditable(true);


        borderPane = new BorderPane();

        borderPane.setCenter(this);

        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<GoodsClassificationVO>>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends TreeItem<GoodsClassificationVO>> change) {


                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            deleteItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addItem(change.getList());
                    }
                });
                modify.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            modifyItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });

                addGood.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            addGoodItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (NotBoundException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


        VBox buttons = new VBox();
        buttons.getChildren().addAll(add, delete, modify, addGood);

        borderPane.setRight(buttons);

        return borderPane;
    }

    public CheckBoxTreeItem<GoodsClassificationVO> getTreeRoot() {
        return root;
    }

    public void setBoardController(BoardController myBoardController) {
        this.myBoardController = myBoardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setGoodsClassificationblService(GoodsClassificationblService goodsClassificationblService) {
        this.goodsClassificationblService = goodsClassificationblService;
    }

    public void setGoodsClassificationVO(List<GoodsClassificationVO> list) {
        this.classificationList = list;
    }

    protected void deleteItem(ObservableList<? extends TreeItem<GoodsClassificationVO>> list) throws RemoteException {
        if(list.size() == 0){
            warningDialog("请先选择商品分类！");
            return;
        }

        CheckBoxTreeItem<GoodsClassificationVO> item = (CheckBoxTreeItem<GoodsClassificationVO>) list.get(0);

        CheckBoxTreeItem<GoodsClassificationVO> parent = (CheckBoxTreeItem<GoodsClassificationVO>) item.getParent();

        if(!item.isLeaf()){
            warningDialog("该分类下存在商品或者分类，不能删除！！");
            return;
        }

        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "警告", "确认删除？", "确认", "取消");
        doubleButtonDialog.setButtonOne(() -> {
            //连同changeList中的数据也要删掉，否则会报错
            parent.getChildren().remove(item);
            list.remove(item);
            doubleButtonDialog.close();

            GoodsClassificationVO son = item.getValue();
            GoodsClassificationVO father = parent.getValue();

            try {
                goodsClassificationblService.deleteGoodsClassification(son);
            } catch (RemoteException e1) { e1.printStackTrace(); }

            List<String> childrensId = father.getChildrenId();

            childrensId.remove(son.getID());

            father.setChildrenId(childrensId);

            try {
                goodsClassificationblService.updateGoodsClassification(father);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });

        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.show();

    }

    protected void addItem(ObservableList<? extends TreeItem<GoodsClassificationVO>> list) {
        if(list.size() == 0){
            warningDialog("请先选择商品分类！");
            return;
        }

        CheckBoxTreeItem<GoodsClassificationVO> item = (CheckBoxTreeItem<GoodsClassificationVO>) list.get(0);

        GoodsClassificationVO vo = item.getValue();

        if(vo.getGoodsID().size() != 0){
            warningDialog("该分类下有商品，不能添加新分类");
            return;
        }

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(260.0);
        jfxDialogLayout.setHeading(new Label("新增分类名"));
        JFXTextField name = new JFXTextField();
        jfxDialogLayout.setBody(name);
        JFXButton save = new JFXButton("保存");
        JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        save.setOnAction(e -> {
            String classifyName = name.getText();

            CheckBoxTreeItem<String> son = new CheckBoxTreeItem<>(classifyName);

            GoodsClassificationVO goodsClassificationVO = new GoodsClassificationVO();
            goodsClassificationVO.setName(classifyName);

            String sonId = vo.getID()+"/"+vo.getChildrenId().size();
            goodsClassificationVO.setID(sonId);
            goodsClassificationVO.setFatherID(vo.getID());

            List<String> childrenId = vo.getChildrenId();
            childrenId.add(sonId);

            try {
                goodsClassificationblService.addGoodsClassification(goodsClassificationVO);
                goodsClassificationblService.updateGoodsClassification(vo);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            dialog.close();

            BoardController.getBoardController().refresh();
        });
        jfxDialogLayout.setActions(save);
        dialog.show();

    }

    protected void addGoodItem(ObservableList<? extends TreeItem<GoodsClassificationVO>> list) throws RemoteException, MalformedURLException, NotBoundException {
        if(list.size() == 0){
            warningDialog("请先选择商品分类！");
            return;
        }


        CheckBoxTreeItem<GoodsClassificationVO> son = (CheckBoxTreeItem<GoodsClassificationVO>) list.get(0);

        GoodsClassificationVO vo = son.getValue();


        if(vo.getChildrenId().size() != 0){
            warningDialog("该分类下有分类，不能添加商品");
            return;
        }

        goodDetailPane = new GoodDetailPane(true);

        String classifyId = vo.getID();
        goodDetailPane.setClassifyId(classifyId);
        goodDetailPane.setOrder(vo.getGoodsID().size()+1);


    }

    /**
     * 传入子分类名和父分类名，更新数据库
     *
     * @throws
     */
    protected void modifyItem(ObservableList<? extends TreeItem<GoodsClassificationVO>> list) throws RemoteException {
        if(list.size() == 0){
            warningDialog("请先选择商品分类！");
            return;
        }

        CheckBoxTreeItem<GoodsClassificationVO> item = (CheckBoxTreeItem<GoodsClassificationVO>) list.get(0);

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        jfxDialogLayout.setHeading(new Label("商品分类名"));
        JFXTextField name = new JFXTextField();
        name.setText(item.getValue().toString());
        name.setPrefWidth(210);
        jfxDialogLayout.setBody(name);
        JFXButton save = new JFXButton("保存");
        JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

       // String oldName = item.getValue();

        save.setOnAction(e -> {
            String classifyName = name.getText();

            GoodsClassificationVO goodsClassificationVO = item.getValue();

            goodsClassificationVO.setName(classifyName);

            item.setValue(goodsClassificationVO);

            dialog.close();

            try {
                goodsClassificationblService.updateGoodsClassification(goodsClassificationVO);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }

            BoardController.getBoardController().refresh();
        });
        jfxDialogLayout.setActions(save);
        dialog.show();

    }


    /**
     * 修改分类信息时，更新数据库
     *
     * @param newName
     * @param oldName
     */
    private void updateGoodsClassificationVO(String newName, String oldName) throws RemoteException {
        for (GoodsClassificationVO vo : classificationList) {
            if (vo.getName().equals(oldName)) {
                vo.setName(newName);
                goodsClassificationblService.updateGoodsClassification(vo);
                break;
            }
        }

    }

    private CheckBoxTreeItem<GoodsClassificationVO> buildTree() throws RemoteException {
        Queue<GoodsClassificationVO> que = new LinkedList<>();

        GoodsClassificationVO first = classificationList.get(0);

        que.offer(classificationList.get(0));

        root = new CheckBoxTreeItem<>(first);

        CheckBoxTreeItem<GoodsClassificationVO> item = root;

        int index = 1;
        int size = classificationList.size();
        while (!que.isEmpty()) {
            GoodsClassificationVO father = que.poll();
            String fatherId = father.getID();
            String fatherName = father.getName();

            if (index != 1) {
                item = getCheckItem(root, fatherId);
            }

            for (; index < size; index++) {
                    GoodsClassificationVO son = classificationList.get(index);

                if (son.getFatherID().equals(fatherId)) {
                    CheckBoxTreeItem<GoodsClassificationVO> sonTreeItem = new CheckBoxTreeItem<>(son,rootIcon);
                    sonTreeItem.setIndependent(true);
                   // sonTreeItem.setGraphic(rootIcon);

                    item.getChildren().add(sonTreeItem);
                    que.offer(son);

                    if (son.getGoodsID() != null)
                        sonTreeItem = buildLeaf(sonTreeItem, son);
                } else {
                    break;
                }
            }
        }

        return root;
    }

    private CheckBoxTreeItem<GoodsClassificationVO> buildLeaf(CheckBoxTreeItem<GoodsClassificationVO> sonTreeItem, GoodsClassificationVO son) throws RemoteException {
        List<String> goodsId = son.getGoodsID();
        for (String goodId : goodsId) {
            GoodsVO goodsVO = new GoodsVO();
            if(goodId.equals(""))
                continue;

            goodsVO = goodsbl.showDetail(goodId);

            GoodsClassificationVO goodsClassificationVO = new GoodsClassificationVO();
            goodsClassificationVO.setName(goodId);
            goodsClassificationVO.setName(goodsVO.getGoodName());

            CheckBoxTreeItem<GoodsClassificationVO> goodTreeItem = new CheckBoxTreeItem<>(goodsClassificationVO);
            goodTreeItem.setIndependent(true);
            sonTreeItem.getChildren().add(goodTreeItem);
        }
        return sonTreeItem;
    }

    //递归调用获取fatherId对应的节点
    private CheckBoxTreeItem<GoodsClassificationVO> getCheckItem(CheckBoxTreeItem<GoodsClassificationVO> currentRoot, String fatherId) {
        for (Iterator<TreeItem<GoodsClassificationVO>> iterator = currentRoot.getChildren().iterator(); iterator.hasNext(); ) {

            CheckBoxTreeItem tmp = (CheckBoxTreeItem<GoodsClassificationVO>) iterator.next();

            GoodsClassificationVO goodsClassificationVO = (GoodsClassificationVO) tmp.getValue();

            if (goodsClassificationVO.getID().equals(fatherId))
                return tmp;
            else {
                tmp = getCheckItem(tmp, fatherId);
                if (tmp != null)
                    return tmp;
            }
        }
        return null;
    }

    private void warningDialog(String infomation){

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(300.0);
        jfxDialogLayout.setHeading(new Label("警告"));
        JFXTextField name = new JFXTextField();
        jfxDialogLayout.setBody(new JFXTextField(infomation));
        JFXButton back = new JFXButton("取消");
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        back.setOnAction(e -> {
            dialog.close();
        });
        jfxDialogLayout.setActions(back);
        dialog.show();
    }




}
