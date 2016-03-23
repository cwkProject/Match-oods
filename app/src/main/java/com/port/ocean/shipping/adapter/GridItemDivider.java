package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/16.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 表格布局的分割线
 *
 * @author 超悟空
 * @version 1.0 2016/3/16
 * @since 1.0
 */
public class GridItemDivider extends RecyclerView.ItemDecoration {

    /**
     * 分割条绘图工具
     */
    private Drawable dividerDrawable = null;

    /**
     * 分割线大小
     */
    private int dividerSize = 2;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public GridItemDivider(Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        dividerDrawable = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        for (int i = 0, size = parent.getChildCount(); i < size; i++) {
            View child = parent.getChildAt(i);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            // 画下边
            int left = child.getLeft();
            int right = child.getRight();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + dividerSize;

            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(c);

            // 画右边
            if (parent.getPaddingRight() - child.getRight() > child.getWidth()) {
                left = child.getRight() + params.rightMargin;
                right = left + dividerSize;
                top = child.getTop();
                bottom = child.getBottom() + dividerSize;

                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getPaddingRight() - view.getRight() < view.getWidth()) {
            outRect.set(0, 0, 0, dividerSize);
        } else {
            outRect.set(0, 0, dividerSize, dividerSize);
        }
    }
}
