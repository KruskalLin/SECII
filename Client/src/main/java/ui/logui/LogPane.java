package ui.logui;

import blService.logblService.LogblService;
import businesslogic.logbl.Logbl;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.InlineCssTextArea;
import org.fxmisc.richtext.LineNumberFactory;
import po.LogPO;
import ui.common.bigPane.GatePane;
import ui.util.ArrowFactory;
import util.EventCategory;
import util.UserCategory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.IntFunction;

public class LogPane extends GatePane {


    LogblService logblService;

    ArrayList<LogPO> list;

    @FXML
    InlineCssTextArea textArea;

    public LogPane(){
        super();
        textArea.autosize();
    }

    @Override
    protected String getURL() {
        return "/logui/logpane.fxml";
    }

    @Override
    protected void refreshAfterMath() {
        setList(list);
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        logblService = new Logbl();
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        setList(logblService.getList());
    }

    public ArrayList<LogPO> getList() {
        return list;
    }

    public void setList(ArrayList<LogPO> list) {
        this.list = list;
        textArea.clear();
        for(LogPO logPO:list){
            textArea.appendText(logPO.toString());
        }
    }

}
