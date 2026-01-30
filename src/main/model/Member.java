package com.GymManagement.GymManagement.model;

public class Member {
    private int memberId;
    private String name;
    private String email;
    private String password;
    private String startMonth;
    private String endMonth;

    public Member(int memberId, String startMonth, String endMonth, boolean isActive, String slotTime) {
        this.memberId = memberId;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.isActive = isActive;
        this.slotTime = slotTime;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    private boolean isActive;
    private String slotTime;

    public Member() {
    }

    public Member(int memberId, String name, String email, String password) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
