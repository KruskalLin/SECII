package po.generic;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class ReceipishPO implements Serializable {
    private int dayId;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    protected ReceipishPO() {
    }

    protected ReceipishPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime) {
        this.dayId = dayId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) { // TODO dayId也要这么干
//        if (this.createTime == null) {
            this.createTime = createTime;
//        }
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
