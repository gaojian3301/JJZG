package jjzg.app.qinggan.com.jiejinzhiguang.ch2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

public class CustomView extends View {
    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            Log.d("gaojian","mScroller.getCurrX() = "+mScroller.getCurrX()+"mScroller.getCurrY() = "+mScroller.getCurrY());
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollerTo(int destX,int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        Log.d("gaojian","smoothScrollerTo scrollX = "+scrollX+",delta = "+delta);
        mScroller.startScroll(scrollX,0,delta,0,15000);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.d("gaojian","ACTION_DOWN x = "+x+", y = "+y);
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                //layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);

                ((View)getParent()).scrollBy(-offsetX,-offsetY);
                break;
            default:
                break;
        }
        return true;
    }
}
