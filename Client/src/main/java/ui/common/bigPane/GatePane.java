package ui.common.bigPane;

import businesslogic.blServiceFactory.FactoryController;
import ui.common.BoardController;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public abstract class GatePane extends FXMLRefreshableAnchorPane {
    private boolean firstLoad = true;

    public GatePane() {
        initiateFields();
    }

    /**
     * abstract methods
     */

    protected abstract void refreshAfterMath();

    protected abstract void initiateService() throws RemoteException, NotBoundException, MalformedURLException;

    protected abstract void updateDataFromBl() throws RemoteException;

    /**
     * to be overridden
     */

    // The method is aimed to designate fields before super constructors are called
    protected void initiateFields() {
    }


    /**
     * refresh
     */
    @Override
    public void refresh(boolean toSwitch) { // toSwitch没用
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            myBoardController.switchTo(this);
            refreshAfterMath();
        }, buttonDialog, woid -> {
            try {
                if (firstLoad) {
                    initiateService();
                    firstLoad = false;
                }
                updateDataFromBl();
                return true;
            } catch (RemoteException | NotBoundException | MalformedURLException e) {
                e.printStackTrace();
                FactoryController.clearAllSavedService();
                return false;
            }
        })).start();
    }


}
