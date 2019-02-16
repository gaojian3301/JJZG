package jjzg.app.qinggan.com.jiejinzhiguang.ch1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

public class PullRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
    public PullRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recyclerView = new RecyclerView(context,attrs);
        LinearLayoutManager mannagerTwo = new LinearLayoutManager(context);
        mannagerTwo.setOrientation(LinearLayoutManager.HORIZONTAL);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.setLayoutManager(mannagerTwo);
        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    private int getFirstVisiblePosition(){
        int position = 0;
        RecyclerView.LayoutManager manager = mRefreshableView.getLayoutManager();
        if (manager instanceof LinearLayoutManager){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            return linearLayoutManager.findFirstVisibleItemPosition();
        }
        return position;
    }

    private boolean isFirstItemVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        if (null == adapter || adapter.getItemCount() ==0) {
            return true;
        } else {
            if (getFirstVisiblePosition() <= 1) {
                final View firstVisibleChild = mRefreshableView.getChildAt(0);
                if (firstVisibleChild != null) {
                    return firstVisibleChild.getLeft() >= mRefreshableView.getLeft();
                }
            }
        }
        return false;
    }


    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration){
        mRefreshableView.addItemDecoration(itemDecoration);
    }
    public void setAdapter(RecyclerView.Adapter adapter){
        mRefreshableView.setAdapter(adapter);
    }

}
