package com.softgarden.baselibrary.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Lightwave on 2016/7/5.
 */
public class ColorDividerDecoration extends RecyclerView.ItemDecoration {
    /**
     * @hide
     */
    @IntDef(flag = true,
            value = {
                    SHOW_DIVIDER_BEGINNING_MIDDLE,
                    SHOW_DIVIDER_MIDDLE,
                    SHOW_DIVIDER_MIDDLE_END,
            })
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    /**
     * SHOW_DIVIDER_MIDDLE_END
     */
    public static final int SHOW_DIVIDER_MIDDLE_END = 0;

    public static final int SHOW_DIVIDER_MIDDLE = 1;
    /**
     * SHOW_DIVIDER_BEGINNING_MIDDLE
     */
    public static final int SHOW_DIVIDER_BEGINNING_MIDDLE = 2;


    private int showDividers;//defalut= SHOW_DIVIDER_BEGINNING_MIDDLE

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private int mOrientation;
    private float dividerHeight;
    private Paint paint;


    public ColorDividerDecoration() {
        this(VERTICAL_LIST);
    }

    public ColorDividerDecoration(int orientation) {
        this(orientation, Color.BLACK, 1);
    }

    public ColorDividerDecoration(@ColorInt int divider, int dividerHeight) {
        this(VERTICAL_LIST, divider, dividerHeight);
    }

    public ColorDividerDecoration(int orientation, @ColorInt int divider, float dividerHeight) {

        this(orientation, divider, dividerHeight, SHOW_DIVIDER_MIDDLE_END);
    }

    public ColorDividerDecoration(int orientation, @ColorInt int divider, float dividerHeight, int showDividers) {
        setOrientation(orientation);
        paint = new Paint();
        paint.setColor(divider);
        paint.setAntiAlias(true);
        this.dividerHeight = dividerHeight;
        this.showDividers = showDividers;
    }


    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        childCount = showDividers == SHOW_DIVIDER_MIDDLE ? childCount - 1 : childCount;//不画最后一个

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = (int) (top + dividerHeight);
            c.drawRect(left, top, right, bottom, paint);
        }
    }


    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        int childCount = parent.getChildCount();
        childCount = showDividers == SHOW_DIVIDER_MIDDLE ? childCount - 1 : childCount;//不画最后一个

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = (int) (left + dividerHeight);
            c.drawRect(left, top, right, bottom, paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            if (showDividers == SHOW_DIVIDER_BEGINNING_MIDDLE) {
                outRect.set(0, (int) dividerHeight, 0, 0);
            } else {
                outRect.set(0, 0, 0, (int) dividerHeight);
            }
        } else {
            if (showDividers == SHOW_DIVIDER_BEGINNING_MIDDLE) {
                outRect.set((int) dividerHeight, 0, 0, 0);
            } else {
                outRect.set(0, 0, (int) dividerHeight, 0);
            }
        }
    }
}
