package com.example.lenovo.accounting;

public class AccountInItem {
    private int id;
    private String curMark;
    private String curIn;
    private String curDate;

    public AccountInItem() {
        this.curMark = "";
        this.curIn = "";
        this.curDate = "";
    }

    public AccountInItem(String curMark, String curIn, String curDate) {
        this.curMark = curMark;
        this.curIn =curIn;
        this.curDate = curDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurMark() {
        return curMark;
    }

    public void setCurMark(String curMark) {
        this.curMark = curMark;
    }

    public String getCurIn() {
        return curIn;
    }

    public void setCurIn(String curIn) {
        this.curIn = curIn;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }
}
