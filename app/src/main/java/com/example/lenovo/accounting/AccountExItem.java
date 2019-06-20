package com.example.lenovo.accounting;

public class AccountExItem {
    private int id;
    private String curMark;
    private String curEx;
    private String curDate;

    public AccountExItem() {
        this.curMark = "";
        this.curEx = "";
        this.curDate = "";
    }

    public AccountExItem(String curMark, String curEx, String curDate) {
        this.curMark = curMark;
        this.curEx = curEx;
        this.curDate = curDate;
    }

    public int getId() {
        return id;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
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

    public String getCurEx() {
        return curEx;
    }

    public void setCurEx(String curEx) {
        this.curEx = curEx;
    }
}
