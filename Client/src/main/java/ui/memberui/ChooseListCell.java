package ui.memberui;

import com.jfoenix.controls.JFXListCell;
import vo.MemberVO;

public class ChooseListCell extends JFXListCell<MemberVO> {
    private ChooseListHBox chooseListCell;
    @Override
    public synchronized void updateItem(MemberVO item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
        {
            chooseListCell = new ChooseListHBox();
            chooseListCell.setName(item.getName());
            chooseListCell.setImage(item.getImage());
            chooseListCell.setId(item.getMemberId());
            chooseListCell.setDegree(item.getDegree());
            chooseListCell.setComment(item.getComment());
            setGraphic(chooseListCell);
        }else{
            setGraphic(null);
        }

    }
}
