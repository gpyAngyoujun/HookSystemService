package com.jimmy.library.service;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

import com.jimmy.library.IProxyService;

/**
 * @author yangyoujun
 * @date 18-1-27
 */
public class AudioManagerProxy extends AudioManager implements IProxyService {

    private Object mSour;
    private static final String TAG = "AudioManagerProxy";

    public AudioManagerProxy(Context context) {
        super(context);
    }

    @Override
    public int requestAudioFocus(OnAudioFocusChangeListener l, int streamType, int durationHint) {
        Log.i(TAG, "requestAudioFocus: streamType:" + streamType + " durationHint:" + durationHint);
        return AUDIOFOCUS_REQUEST_FAILED;
    }

    @Override
    public void set(Object sour) {
        mSour = sour;
    }

    @Override
    public Object get() {
        return mSour;
    }
}
