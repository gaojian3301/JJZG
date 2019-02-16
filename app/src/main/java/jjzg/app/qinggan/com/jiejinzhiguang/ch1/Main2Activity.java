package jjzg.app.qinggan.com.jiejinzhiguang.ch1;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jjzg.app.qinggan.com.jiejinzhiguang.R;

public class Main2Activity extends Activity {
    private RelativeLayout outer;
    private ImageView imageView;
    private PullRefreshRecyclerView pullRefreshRecyclerView;
    private MyAdapter adapter;
    private Handler mHandler;
    private Integer iii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        List<AppWidgetProviderInfo> widgets = AppWidgetManager.getInstance(getApplicationContext()).getInstalledProviders();
        for(AppWidgetProviderInfo widget : widgets){
            Log.d("gaojian","widget = "+widget);
        }
        outer = findViewById(R.id.outer);
        pullRefreshRecyclerView = findViewById(R.id.pull_refresh);
        List<String> datas = new ArrayList<>();
        datas.addAll(Arrays.asList("aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","jjj"));
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case 1:
                        Log.d("gaojian","msg  1");
                        outer.setVisibility(View.VISIBLE);
                        List<String> datas = new ArrayList<>();
                        datas.addAll(Arrays.asList("aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","jjj"));
                        adapter.updateDatas(datas);
                        pullRefreshRecyclerView.onRefreshComplete();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        adapter = new MyAdapter(this);
        pullRefreshRecyclerView.setAdapter(adapter);
        pullRefreshRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Log.d("gaojian","onRefresh");
                Log.d("gaojian","onRefresh1111");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            Log.d("gaojian","sendEmptyMessage");
                            mHandler.sendEmptyMessage(1);
                        } catch (InterruptedException e) {
                            Log.d("gaojian","e "+e);
                            e.printStackTrace();
                        }

                    }
                }).start();

            }
        });
        mHandler.sendEmptyMessageDelayed(1,3000);
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder>{
        private Context mContext;
        private List<String> datas;

        public MyAdapter(Context context){
            mContext = context;
        }

        public MyAdapter(Context context,List<String> lists){
            mContext = context;
            datas = lists;
        }

        public void updateDatas(List<String> lists){
            datas = lists;
            notifyDataSetChanged();
        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.ch1_recycler_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.content.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            if(datas != null) {
                return datas.size();
            }
            return 0;
        }
    }

    class MyHolder extends RecyclerView.ViewHolder{
        View view;
        TextView content;
        public MyHolder(View itemView) {
            super(itemView);
            view = itemView;
            content = itemView.findViewById(R.id.content);
        }
    }

}
