package vo.abstractVO;

import po.generic.ReceipishPO;

import java.time.LocalDateTime;

public abstract class ReceipishVO<TR> extends SelectableVO<TR> {
    protected String id;
    protected LocalDateTime createTime,lastModifiedTime;

    protected ReceipishVO() {
    }

    protected ReceipishVO(ReceipishPO receipishPO) {
        id = formatId(receipishPO);
        createTime = receipishPO.getCreateTime();
        lastModifiedTime = receipishPO.getLastModifiedTime();
    }

    protected abstract String getCodeName();

    public abstract <TF extends ReceipishPO> TF toPO();

    // 这里不能搞继承，而且应该和constructor一样，每个都叫不同的名字
    // TODO 这里应该可以更进一步，反射获得Field名字和类型相同的就直接赋值
    protected <TF extends ReceipishPO> TF toReceipishPO(Class<TF> receipishClass) {
        TF result = null;
        try {
            result = receipishClass.newInstance();
            result.setDayId(idToDayId());
            result.setCreateTime(createTime);
            result.setLastModifiedTime(lastModifiedTime);
        } catch (IllegalAccessException|InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String formatId(ReceipishPO receipishPO) {
        LocalDateTime createTime = receipishPO.getCreateTime();
        return String.format(getCodeName() + "-%04d%02d%02d-%05d"
                , createTime.getYear()
                , createTime.getMonthValue()
                , createTime.getDayOfMonth()
                , receipishPO.getDayId());
    }

    protected int idToDayId() {
        return Integer.parseInt(id.substring(id.length() - 5));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
