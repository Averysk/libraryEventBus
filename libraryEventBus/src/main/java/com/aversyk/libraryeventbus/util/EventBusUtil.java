package com.aversyk.libraryeventbus.util;

import android.app.Activity;
import android.os.CountDownTimer;

import com.aversyk.libraryeventbus.EventBusActivityScope;

/**
 * @Description: 事件管理工具
 * @author: Averysk
 */
public class EventBusUtil {
    //注册事件
    public static void register(Activity activity, Object context) {
        if (activity == null) {
            return;
        }
        EventBusActivityScope.getDefault(activity).register(context);
    }
    //解除
    public static void unregister(Activity activity, Object context) {
        if (activity == null) {
            return;
        }
        EventBusActivityScope.getDefault(activity).unregister(context);
    }
    //发送消息
    public static void post(Activity activity, IEvent object) {
        if (activity == null) {
            return;
        }
        EventBusActivityScope.getDefault(activity).post(object);
    }
    //发送粘性事件
    public static void postSticky(Activity activity, IEvent object) {
        if (activity == null) {
            return;
        }
        EventBusActivityScope.getDefault(activity).postSticky(object);
    }

    //发送延迟消息(默认延迟1秒)
    public static void postDelayed(Activity activity, IEvent object) {
        // 倒计时器 总时1秒 间隔1秒
        new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                // 关闭计时器
                cancelTimer(this);
                postSticky(activity, object);
            }
        }.start();

    }

    //发送延迟消息
    public static void postDelayed(Activity activity, IEvent object, long s) {
        new CountDownTimer(s, s) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                // 关闭计时器
                cancelTimer(this);
                postSticky(activity, object);
            }
        }.start();
    }

    /**
     * 关闭计时器
     */
    public static void cancelTimer(CountDownTimer mTimer) {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

}
