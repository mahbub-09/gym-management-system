package com.GymManagement.GymManagement.model;

public class Subscription {
    private int id;
    private int memberId;
    private int slotId;
    private String startMonth;
    private String endMonth;

    public Subscription() {
    }

    public Subscription(int id, int memberId, int slotId, String startMonth, String endMonth) {
        this.id = id;
        this.memberId = memberId;
        this.slotId = slotId;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setSlotTime(String slotTime) {
    }
}