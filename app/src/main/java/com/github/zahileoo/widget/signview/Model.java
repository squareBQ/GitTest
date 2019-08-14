package com.github.zahileoo.widget.signview;

/**
 * Created by zahi on 2018/7/31.
 */
public class Model {
    private String day;
    private int textColor;
    private int backgroundResId;

    public Model(String day, int textColor, int backgroundResId) {
        this.day = day;
        this.textColor = textColor;
        this.backgroundResId = backgroundResId;
    }

    public Model(String day, boolean isSign) {
        this.day = day;
        this.textColor = isSign ? SignCalendarView.signTextColor : SignCalendarView.unSignTextColor;
        this.backgroundResId = isSign ? SignCalendarView.signResId : SignCalendarView.unSignResId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundResId() {
        return backgroundResId;
    }

    public void setBackgroundResId(int backgroundResId) {
        this.backgroundResId = backgroundResId;
    }
}
