package jjzg.app.qinggan.com.jiejinzhiguang.ch2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import jjzg.app.qinggan.com.jiejinzhiguang.R;

public class Main3Activity extends Activity {

    private Handler mHandler;
    private CustomView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mHandler = new Handler(mCallback);
        view = findViewById(R.id.moving);
        mHandler.sendEmptyMessageDelayed(1,3000);
    }

    private Handler.Callback mCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    view.smoothScrollerTo(-400,0);
                    break;
                    default:
                        break;
            }
            return false;
        }
    };
}
