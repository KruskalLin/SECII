package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import sun.java2d.pipe.SpanShapeRenderer;
import util.UserCategory;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;
import java.util.Comparator;

public class UserListVO extends SelectableVO<UserListVO> {

    private int userID;
    private Image image;
    private String userName;
    private UserCategory userCategory;
    private String email;
    private String phone;
    private boolean isDelete;
    private boolean multiple;
    private UserVO userVO;

    public UserListVO(){
        this.setImage(new Image("/default/timg.jpg"));
    }

    public UserListVO(String userName, UserCategory userCategory, String email, String phone) {
        this.setImage(new Image("/default/timg.jpg"));
        this.userName = userName;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
    }

    public UserListVO(int userID, Image image, String userName, UserCategory userCategory, String email, String phone, boolean isDelete) {
        this.userID = userID;
        this.image = image;
        this.userName = userName;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
        this.isDelete = isDelete;
    }

    public UserListVO(UserVO userVO) {
        this.userVO = userVO;
        this.setUserName(userVO.getUsername());
        this.setUserCategory(userVO.getUsertype());
        this.setImage(userVO.getImage());
        this.setPhone(userVO.getPhone());
        this.setEmail(userVO.getEmail());
        this.setUserID(userVO.getId());
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public UserVO toVO(){
        return this.userVO;
    }
}
