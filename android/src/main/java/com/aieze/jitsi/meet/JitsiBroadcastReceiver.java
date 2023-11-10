package com.aieze.jitsi.meet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class JitsiBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "JitsiBroadcastReceiver";
    private CustomJitsiMeetPlugin jitsi;

    public void setModule(CustomJitsiMeetPlugin module) {
        this.jitsi = module;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String eventName = (String) intent.getSerializableExtra("eventName");
        if (jitsi != null) {
            jitsi.onEventReceived(eventName);
        }
    }
}
