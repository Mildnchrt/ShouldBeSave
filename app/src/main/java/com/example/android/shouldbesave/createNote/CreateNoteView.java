package com.example.android.shouldbesave.createNote;

/**
 * Created by mildnchrt on 5/30/2017 AD.
 */

public interface CreateNoteView {
    String getTitleText();
    String getDescriptionText();
    int getMoneyText();
    int getTypeText();

    void setEditTitle(String t);
    void setEditDescription(String d);
    void setEditMoney(int m);
    void setEditType(int t);

    void setEditTitleHint(String t);
    void setEditDescriptionHint(String d);
    void setEditMoneyHint(String m);
    void setEdiTypeHint();
}
