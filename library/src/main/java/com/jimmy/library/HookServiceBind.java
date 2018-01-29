package com.jimmy.library;

import android.content.Context;

/**
 * @author yangyoujun
 * @date 18-1-27
 */
class HookServiceBind extends HookService {

    HookServiceBind(Context context) {
        super(context);
    }

    @Override
    public <T extends IProxyService> Object proxy(T proxy) throws IllegalAccessException {
        Context impl = HookServiceHelper.getImpl(mContext);
        Object actual = mContext.getSystemService(mService);
        if (proxy != null) {
            proxy.set(actual);
        }
        Object[] services;

        synchronized (this) {
            if (sServiceCacheField == null) {
                sServiceCacheField = HookServiceHelper.getServiceCacheField(impl);
            }
            services = (Object[]) sServiceCacheField.get(impl);
            for (int i = 0; i < services.length; i++) {
                Object service = services[i];
                if (service != null && actual == service) {
                    services[i] = proxy;
                    break;
                }
            }
        }

        return actual;
    }
}
