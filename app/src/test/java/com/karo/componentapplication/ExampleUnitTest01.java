package com.karo.componentapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.ArrayMap;
import android.util.SparseArray;

import androidx.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ExampleUnitTest01
 * description:
 * created at：10:24 on 2020/10/21
 * author: NKY
 * email: karo.nie@esenyun.com
 */
public class ExampleUnitTest01 {
    private Handler handler;

    @Test
    public void handler() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//            }
//        };
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                handler = new Handler() {
//                    @Override
//                    public void handleMessage(@NonNull Message msg) {
//                        super.handleMessage(msg);
//                        Object obj = msg.obj;
//                    }
//                };
//                Looper.loop();
//            }
//        }).start();
//        Message message = Message.obtain();
//        message.obj = "长江长江，我是黄河！！";
//        message.setAsynchronous(true);
//        handler.sendMessageDelayed(message, 1000);
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.remove("1");
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        boolean empty = list.isEmpty();
//        List<String> list1 = Collections.synchronizedList(list);


        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1, "akjlfd");
        sparseArray.put(3, "akjlfd");
        sparseArray.put(5, "akjlfd");

        ArrayMap<String, String> arrayMap = new ArrayMap<>();
    }
}
