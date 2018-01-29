package com.jimmy.library;

import android.content.Context;

import java.util.Objects;

/**
 * @author yangyoujun
 * @date 18-1-27
 */
class HookServiceUnbind extends HookService {

    HookServiceUnbind(Context context) {
        super(context);
    }

    @Override
    public <T extends IProxyService> Object proxy(T proxy) throws IllegalAccessException {
        Objects.requireNonNull(proxy);
        Context impl = HookServiceHelper.getImpl(mContext);
        Object actual = proxy.get();
        if (actual == null) {
            return null;
        }

        Object[] services;

        synchronized (this) {
            if (sServiceCacheField == null) {
                sServiceCacheField = HookServiceHelper.getServiceCacheField(impl);
            }
            services = (Object[]) sServiceCacheField.get(impl);
            for (int i = 0; i < services.length; i++) {
                Object service = services[i];
                if (service != null && proxy == service) {
                    services[i] = actual;
                    break;
                }
            }
        }

        return actual;
    }
}
