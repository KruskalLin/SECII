package ui.mainui;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import ui.common.bigPane.FXMLAnchorPane;
import ui.util.NodeHolder;

import java.util.ArrayList;

public class Carousel extends FXMLAnchorPane{
    private CardHolder[] nodes;
    private ArrayList<PromotionCard> arrayList = new ArrayList<>();
    private int index = 0;
    private Duration duration = new Duration(500);
    private int size;

    public Carousel(){
        super();
        this.setPrefSize(600,400);
        init();
    }

    private void init(){
        PromotionCard promotionCard = new TotalPromotionCard();
        arrayList.add(promotionCard);
        promotionCard = new MemberPromotionCard();
        arrayList.add(promotionCard);
        promotionCard = new CombinePromotionCard();
        arrayList.add(promotionCard);
        promotionCard = new MemberPromotionCard();
        arrayList.add(promotionCard);
        promotionCard = new TotalPromotionCard();
        arrayList.add(promotionCard);
        size = arrayList.size();
        nodes = new CardHolder[size];
        if(arrayList.size()==1){
            nodes[0] = new CardHolder(duration,arrayList.get(0),this,CardPlace.Middle);
        }else if(arrayList.size()>1){
            for(int i=arrayList.size()-1;i>=1;i--){
                nodes[i] = new CardHolder(duration,arrayList.get(i),this,CardPlace.Right);
            }
            nodes[0] = new CardHolder(duration,arrayList.get(0),this,CardPlace.Middle);

        }

    }


    @Override
    protected String getURL() {
        return "/salesui/carousel.fxml";
    }

    @FXML
    public void left(){
        if(index+1<size) {
            nodes[index].MTL();
            index++;
            nodes[index].RTM();
        }
    }


    @FXML
    public void right(){
        if(index-1>=0) {
            nodes[index].MTR();
            index--;
            nodes[index].LTM();
        }
    }
}
