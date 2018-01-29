package com.jimmy.hooksystemservice;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jimmy.library.HookService;
import com.jimmy.library.service.AudioManagerProxy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AudioManagerProxy proxy = new AudioManagerProxy(this);
        try {
            HookService.bind(this)
                    .service(Context.AUDIO_SERVICE)
                    .proxy(proxy);

            AudioManager service = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            service.requestAudioFocus(null, 1, 2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        findViewById(R.id.tv_hello_world)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            HookService.unbind(v.getContext())
                                    .service(Context.AUDIO_SERVICE)
                                    .proxy(proxy);

                            AudioManager service = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                            service.requestAudioFocus(null, 1, 2);
                            Log.i("AudioManagerProxy", "onClick: 123");
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
