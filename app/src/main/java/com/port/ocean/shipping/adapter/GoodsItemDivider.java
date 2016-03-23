package com.port.ocean.shipping.adapter;
/**
 * Created by 超悟空 on 2016/3/10.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 货源列表分割线
 *
 * @author 超悟空
 * @version 1.0 2016/3/10
 * @since 1.0
 */
public class GoodsItemDivider extends RecyclerView.ItemDecoration {
    /**
     * 分割条绘图工具
     */
    private Drawable dividerDrawable = null;

    /**
     * 分割线高度
     */
    private int dividerHeight = 2;

    /**
     * 构造函数
     *
     * @param context 上下文
     */
    public GoodsItemDivider(Context context) {
        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        dividerDrawable = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        for (int i = 0, size = parent.getChildCount(); i < size; i++) {
            View child = parent.getChildAt(i);
            GoodsFindItemViewHolder childViewHolder = (GoodsFindItemViewHolder) parent
                    .getChildViewHolder(child);

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            final int left = childViewHolder.contentLayout.getLeft();
            final int right = childViewHolder.contentLayout.getRight();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + dividerHeight;

            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(c);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, dividerHeight);
    }
}
