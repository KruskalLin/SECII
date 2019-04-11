package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogPO implements Serializable{
    private int logId;
    private LocalDateTime createTime;
    private String username;
    private UserCategory userCategory;
    private EventCategory eventCategory;
    private String event;

    public LogPO() {
    }

    public LogPO(int logId, LocalDateTime createTime, String username, UserCategory userCategory,EventCategory eventCategory, String event) {
        this.logId = logId;
        this.createTime = createTime;
        this.username = username;
        this.userCategory = userCategory;
        this.event = event;
    }

    public LogPO(LocalDateTime createTime, String username, UserCategory userCategory, String event) {
        this.createTime = createTime;
        this.username = username;
        this.userCategory = userCategory;
        this.event = event;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return  logId + "    创立时间： " + createTime.toString() +
                "      用户名： " + username +
                "      用户类型： " + UserCategory.chinese.get(userCategory) + "\n" +
                "事件类型： " + EventCategory.map.get(eventCategory) + "\n" +
                "事件备注： " + event + "\n" +
                "----------------------------------------------------------" + "\n" +
                "----------------------------------------------------------" + "\n";

    }

}
