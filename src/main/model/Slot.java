package com.GymManagement.GymManagement.model;

public class Slot {
    private int id;
    private String slotTime;

    public Slot() {
    }

    public Slot(int id, String slotTime) {
        this.id = id;
        this.slotTime = slotTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }
}
