package com.jimmy.library;

import android.content.Context;
import android.content.ContextWrapper;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author yangyoujun
 * @date 18-1-27
 */
class HookServiceHelper {

    static Context getImpl(Context context) {
        Context nextContext;
        while ((context instanceof ContextWrapper) &&
                (nextContext = ((ContextWrapper) context).getBaseContext()) != null) {
            context = nextContext;
        }
        return context;
    }

    private static final String SERVICE_CACHE = "mServiceCache";

    static Field getServiceCacheField(Context impl) {
        Objects.requireNonNull(impl);
        Field sServiceCacheField = null;
        try {
            sServiceCacheField = impl.getClass().getDeclaredField(SERVICE_CACHE);
            sServiceCacheField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return sServiceCacheField;
    }
}
