package util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserSearchCondition implements Serializable{
    private LocalDateTime createTimeFloor, createTimeCeil;
    private UserCategory userCategory;

    public UserSearchCondition() {
    }

    public UserSearchCondition(LocalDateTime createTimeFloor, LocalDateTime createTimeCeil, UserCategory userCategory) {
        this.createTimeFloor = createTimeFloor;
        this.createTimeCeil = createTimeCeil;
        this.userCategory = userCategory;
    }

    public LocalDateTime getCreateTimeFloor() {
        return createTimeFloor;
    }

    public void setCreateTimeFloor(LocalDateTime createTimeFloor) {
        this.createTimeFloor = createTimeFloor;
    }

    public LocalDateTime getCreateTimeCeil() {
        return createTimeCeil;
    }

    public void setCreateTimeCeil(LocalDateTime createTimeCeil) {
        this.createTimeCeil = createTimeCeil;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
