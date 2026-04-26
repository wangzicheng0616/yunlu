package com.example.ryservice.model;


import java.util.ArrayList;
import java.util.List;

public class RyOrderSyncResult {

    private int total;

    private int saved;

    private int skipped;

    private List<String> orderNos = new ArrayList<>();

    /**
     * 获取total。
     *
     * @return total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置total。
     *
     * @param total total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 获取saved。
     *
     * @return saved
     */
    public int getSaved() {
        return saved;
    }

    /**
     * 设置saved。
     *
     * @param saved saved
     */
    public void setSaved(int saved) {
        this.saved = saved;
    }

    /**
     * 获取skipped。
     *
     * @return skipped
     */
    public int getSkipped() {
        return skipped;
    }

    /**
     * 设置skipped。
     *
     * @param skipped skipped
     */
    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    /**
     * 获取orderNos。
     *
     * @return orderNos
     */
    public List<String> getOrderNos() {
        return orderNos;
    }

    /**
     * 设置orderNos。
     *
     * @param orderNos orderNos
     */
    public void setOrderNos(List<String> orderNos) {
        this.orderNos = orderNos;
    }
}
