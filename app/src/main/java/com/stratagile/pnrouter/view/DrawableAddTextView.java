package com.stratagile.pnrouter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stratagile.pnrouter.R;

/**
 * Created by zl on 2019/1/2.
 */

public class DrawableAddTextView extends LinearLayout {

    private ImageView icon;
    private TextView titleText;
    private TextView titleRightText;
    private ImageView ivNext;

    public DrawableAddTextView(Context context) {
        super(context);
        init(context);
    }

    public DrawableAddTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initTypeArray(attrs);
    }

    public DrawableAddTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTypeArray(attrs);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.drawble_addpictextview, this, true);
        icon = view.findViewById(R.id.icon);
        titleText = view.findViewById(R.id.title);
        titleRightText = view.findViewById(R.id.righttitle);
        ivNext = view.findViewById(R.id.ivNext);
//        icon.getLayoutParams().width = (int) (context.getResources().getDimension(R.dimen.x30));
//        icon.getLayoutParams().height = (int) (context.getResources().getDimension(R.dimen.x30));
    }

    private void initTypeArray(AttributeSet attrs) {
        TypedArray typedArray=getContext().obtainStyledAttributes(attrs, R.styleable.DrawableMyTextView);
        String text = typedArray.getString(R.styleable.DrawableMyTextView_textmy);
        String textsub = typedArray.getString(R.styleable.DrawableMyTextView_subtitle);

        boolean isShowNext = typedArray.getBoolean(R.styleable.DrawableMyTextView_showNextmy, true);
        setShowNext(isShowNext);
        setTitleText(text);
        setRightTitleText(textsub);
        Drawable d = typedArray.getDrawable(R.styleable.DrawableMyTextView_srcmy);
        if (d != null) {
            icon.setImageDrawable(d);
        }
    }
    public void setIcon(CharSequence text) {

        invalidate();
    }
    public void setTitleText(CharSequence text) {
        titleText.setText(text);
        invalidate();
    }
    public void setRightTitleText(CharSequence text) {
        titleRightText.setText(text);
        invalidate();
    }
    public void setShowNext(boolean showNext) {
        ivNext.setVisibility(showNext? VISIBLE : INVISIBLE);
    }
}
