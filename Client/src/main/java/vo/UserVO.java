package vo;

import javafx.scene.image.Image;
import po.UserPO;
import ui.userui.usermanagerui.UserDetailPane;
import util.ImageConvertor;
import util.UserCategory;

import java.time.LocalDateTime;

public class UserVO {
    private int id;
    private Image image;
    private String username;
    private UserCategory usertype;
    private LocalDateTime createTime;
    private String facebook;
    private String github;
    private String twitter;
    private String email;
    private String phone;
    private String comment;
    private String password;
    private boolean isDeleted = false;


    public UserVO(){

    }

    public UserVO(UserPO po){
        this.id=po.getUserId();
        this.image = ImageConvertor.getFXImage(ImageConvertor.getImage(po.getImage()));
        this.username = po.getUsername();
        this.usertype = po.getUsertype();
        this.facebook = po.getFacebook();
        this.github = po.getGithub();
        this.twitter = po.getTwitter();
        this.email = po.getEmail();
        this.phone = po.getPhone();
        this.comment = po.getComment();
        this.password = po.getPassword();

    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCategory getUsertype() {
        return usertype;
    }

    public void setUsertype(UserCategory usertype) {
        this.usertype = usertype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UserPO toPO(){
        UserPO userPO = new UserPO();
        userPO.setComment(comment);
        userPO.setCreateTime(createTime);
        userPO.setEmail(email);
        userPO.setFacebook(facebook);
        userPO.setGithub(github);
        userPO.setImage(ImageConvertor.getByte(ImageConvertor.getBuffered(image)));
        userPO.setTwitter(twitter);
        userPO.setPassword(password);
        userPO.setPhone(phone);
        userPO.setUserId(id);
        userPO.setUsername(username);
        userPO.setUsertype(usertype);
        userPO.setIsDeleted(isDeleted ?1:0);
        return userPO;
    }

    public UserListVO toListVO(){
        UserListVO userListVO = new UserListVO();
        userListVO.setUserID(id);
        userListVO.setImage(image);
        userListVO.setEmail(email);
        userListVO.setPhone(phone);
        userListVO.setUserCategory(usertype);
        userListVO.setUserName(username);
        userListVO.setUserVO(this);
        return userListVO;
    }

    public UserDetailPane getDetailPane(){
        return new UserDetailPane(this);
    }


}
