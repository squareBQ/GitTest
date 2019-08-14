package com.github.zahileoo.widget.signview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zahi on 2019-06-18.
 */
public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(536870911, -2147483648);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
