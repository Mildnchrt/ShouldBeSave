package com.example.android.shouldbesave.data;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public class Note {
    private String title;
    private String description;
    private int money;
    private int type;

    public Note(String title, String description, int money, int type) {
        this.title = title;
        this.description = description;
        this.money = money;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getType(){ return type; }

    public void setType(int type) { this.type = type; }
}
