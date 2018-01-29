package com.jimmy.library;

import android.content.Context;

import java.lang.reflect.Field;

/**
 * @author yangyoujun
 * @date 18-1-27
 */
public abstract class HookService {

    volatile static Field sServiceCacheField;

    final Context mContext;
    String mService;

    HookService(Context context) {
        mContext = context;
    }

    public static HookService bind(Context context) {
        return new HookServiceBind(context);
    }

    public static HookService unbind(Context context) {
        return new HookServiceUnbind(context);
    }

    public HookService service(String service) {
        mService = service;
        return this;
    }

    public abstract <T extends IProxyService> Object proxy(T proxy) throws IllegalAccessException;

}
