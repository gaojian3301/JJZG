package jjzg.app.qinggan.com.jiejinzhiguang.ch1;

import android.util.Log;

public class Test {

    private Test1 currentTest1;
    public void test(final Test1 test1){
        currentTest1 = test1;
        final String t1 = test1.getName();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("gaojian","t1 = "+t1);
                Log.d("gaojian","test1.name = "+test1.getName());
                Log.d("gaojian","currentTest1.name = "+currentTest1.getName());

            }
        }).start();
    }
}
