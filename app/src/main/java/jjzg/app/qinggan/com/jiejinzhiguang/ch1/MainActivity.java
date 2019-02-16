package jjzg.app.qinggan.com.jiejinzhiguang.ch1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jjzg.app.qinggan.com.jiejinzhiguang.R;

public class MainActivity extends Activity {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private Test test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        swipeRefreshLayout = findViewById(R.id.swipeLayout1);
        recyclerView1 = findViewById(R.id.recycle1);
        recyclerView2 = findViewById(R.id.recycle2);
        recyclerView3 = findViewById(R.id.recycle3);
        setupRecycle1();
        setupRecycle2();
        setupRecycle3();
    }

    private Adapter1 adapter3;
    private void setupRecycle3() {
    }

    private Adapter1 adapter2;
    private void setupRecycle2() {
        recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recyclerView2.addItemDecoration(new android.support.v7.widget.DividerItemDecoration(this,1));
        List<String> datas = new ArrayList<>();
        datas.addAll(Arrays.asList("aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","jjj","kkk","lll"));
        adapter2 = new Adapter1(this,datas);
        recyclerView2.setAdapter(adapter2);
    }

    private Adapter1 adapter1;
    private void setupRecycle1() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView1.addItemDecoration(new DividerItemDecoration(this, LinearLayout.HORIZONTAL));
        List<String> datas = new ArrayList<>();
        datas.addAll(Arrays.asList("aaa","bbb","ccc","ddd","eee","fff","ggg","hhh","iii","jjj"));
        adapter1 = new Adapter1(this,datas);
        adapter1.setClickListener(new Adapter1.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"onItemClick position : "+position,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确认删除吗")
                        .setNegativeButton(" 取消",null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //adapter1.removeData(position);

                                    Intent intent = new Intent("com.pateo.search.navi");
                                    Bundle bundle = new Bundle();
                                    bundle.putString("action", "name");
                                    bundle.putString("searchvalue", "汉庭酒店(武汉江汉大学店)");
                                    //bundle.putString("city_name", "南京");
                                    intent.putExtras(bundle);
                                    sendBroadcast(intent);
                               // startNavi("石榴财智中心",32.053074f,118.75077f);

                            }
                        })
                        .create()
                        .show();
            }
        });
        recyclerView1.setAdapter(adapter1);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

            }
        });
    }

    public void startNavi(String location, float lng, float lat) {
        Intent intent = new Intent("com.pateo.infocenter.navi");
        Bundle bundle = new Bundle();
        bundle.putString("action", "startJourney");
        bundle.putString("coordinate", "mapabc");
        String dests = "[{\"longitude\":" + String.valueOf(lng) + ",\"latitude\":"  + String.valueOf(lat) + ",\"name\":\"" + location + "\"}]";
        bundle.putString("dest", dests);
        intent.putExtras(bundle);
        Log.d("gaojian","dests = "+dests);
        sendBroadcast(intent);
    }
}
