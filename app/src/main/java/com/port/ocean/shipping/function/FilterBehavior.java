package com.port.ocean.shipping.function;
/**
 * Created by 超悟空 on 2016/3/11.
 */

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * 货物筛选布局关联处理器
 *
 * @author 超悟空
 * @version 1.0 2016/3/11
 * @since 1.0
 */
public class FilterBehavior extends CoordinatorLayout.Behavior<View> {

    /**
     * 日志标签前缀
     */
    private static final String LOG_TAG = "FilterBehavior.";

    /**
     * 动画插值器
     */
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    private int sinceDirectionChange;

    public FilterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (dependency instanceof ViewPager) {
            ViewPager viewPager = (ViewPager) dependency;

            if (viewPager.getCurrentItem() != 0) {
                if (child.getVisibility() == View.VISIBLE) {
                    hide(child);
                }
            }
        }

        return false;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.i(LOG_TAG + "layoutDependsOn", "dependency is " + dependency);
        return dependency instanceof ViewPager;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View
            directTargetChild, View target, int nestedScrollAxes) {
        Log.i(LOG_TAG + "onStartNestedScroll", "nestedScrollAxes:" + nestedScrollAxes + " , " +
                "child:" + child);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target,
                                  int dx, int dy, int[] consumed) {
        Log.i(LOG_TAG + "onNestedPreScroll", "dy:" + dy + " , sinceDirectionChange:" +
                sinceDirectionChange);
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
            child.animate().cancel();
            sinceDirectionChange = 0;
        }
        sinceDirectionChange += dy;
        if (sinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (sinceDirectionChange < 0 && child.getVisibility() == View.GONE) {
            show(child);
        }
    }

    /**
     * 隐藏布局
     *
     * @param view 要隐藏的布局
     */
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(view.getHeight()).setDuration
                (100);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    /**
     * 显示布局
     *
     * @param view 要显示的布局
     */
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setDuration(100);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }
}
