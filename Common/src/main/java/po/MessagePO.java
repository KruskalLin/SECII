package po;

import sun.plugin2.message.Message;
import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessagePO {
    private int messageID;
    private LocalDateTime createTime;
    private EventCategory eventCategory;
    private UserCategory userFrom;
    private UserCategory userTo;
    private String userNameFrom;
    private String userNameto;
    private String comment;
    private int hasRead = 0;


    public MessagePO() {
    }

    public MessagePO(int messageID, LocalDateTime createTime, EventCategory eventCategory, UserCategory userFrom, UserCategory userTo, String userNameFrom, String userNameto, String comment, int hasRead) {
        this.messageID = messageID;
        this.createTime = createTime;
        this.eventCategory = eventCategory;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.userNameFrom = userNameFrom;
        this.userNameto = userNameto;
        this.comment = comment;
        this.hasRead = hasRead;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public UserCategory getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(UserCategory userFrom) {
        this.userFrom = userFrom;
    }

    public UserCategory getUserTo() {
        return userTo;
    }

    public void setUserTo(UserCategory userTo) {
        this.userTo = userTo;
    }

    public String getUserNameFrom() {
        return userNameFrom;
    }

    public void setUserNameFrom(String userNameFrom) {
        this.userNameFrom = userNameFrom;
    }

    public String getUserNameto() {
        return userNameto;
    }

    public void setUserNameto(String userNameto) {
        this.userNameto = userNameto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getHasRead() {
        return hasRead;
    }

    public void setHasRead(int hasRead) {
        this.hasRead = hasRead;
    }
}