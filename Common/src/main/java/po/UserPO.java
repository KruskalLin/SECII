package po;


import javafx.scene.image.Image;
import util.ImageConvertor;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserPO implements Serializable{

    private int userId;
    private byte[] image;
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
    private int isDeleted;
    public UserPO() {
    }

    public UserPO(int userId, String username, UserCategory usertype, LocalDateTime createTime, String facebook, String github, String twitter, String email, String phone, String comment, String password,int isDelete) {
        this.userId = userId;
        this.username = username;
        this.usertype = usertype;
        this.createTime = createTime;
        this.facebook = facebook;
        this.github = github;
        this.twitter = twitter;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.password = password;
        this.isDeleted = 0;
    }


    public UserPO(int userId, byte[] image, String username, UserCategory usertype, LocalDateTime createTime, String facebook, String github, String twitter, String email, String phone, String comment, String password,int isDeleted) {
        this.userId = userId;
        this.image = image;
        this.username = username;
        this.usertype = usertype;
        this.createTime = createTime;
        this.facebook = facebook;
        this.github = github;
        this.twitter = twitter;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
