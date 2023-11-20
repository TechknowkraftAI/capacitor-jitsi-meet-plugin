package com.aieze.jitsi.meet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.HashMap;
import javax.annotation.Nullable;
import org.jitsi.meet.sdk.BroadcastEvent;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetView;
import timber.log.Timber;

public class JitsiActivity extends JitsiMeetActivity {

    private BroadcastReceiver broadcastReceiver;
    private static final String TAG = "CapacitorJitsiMeet";
    private static final String ACTION_JITSI_MEET_CONFERENCE = "org.jitsi.meet.CONFERENCE";
    private static final String JITSI_MEET_CONFERENCE_OPTIONS = "JitsiMeetConferenceOptions";
    private static JitsiMeetConferenceOptions session_options;

    @Override
    protected void initialize() {
        broadcastReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    onBroadcastReceived(intent);
                }
            };
        registerForBroadcastMessages();
        join(getConferenceOptions(getIntent()));
    }

    public static void launch(Context context, JitsiMeetConferenceOptions options) {
        session_options = options;
        Intent intent = new Intent(context, JitsiActivity.class);
        intent.setAction(ACTION_JITSI_MEET_CONFERENCE);
        intent.putExtra("JitsiMeetConferenceOptions", options);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private void registerForBroadcastMessages() {
        IntentFilter intentFilter = new IntentFilter();

        for (BroadcastEvent.Type type : BroadcastEvent.Type.values()) {
            intentFilter.addAction(type.getAction());
        }

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }

    private void onBroadcastReceived(Intent intent) {
        JitsiMeetView view = getJitsiView();
        if (intent != null) {
            BroadcastEvent event = new BroadcastEvent(intent);
            switch (event.getType()) {
                case CONFERENCE_JOINED:
                    on("onConferenceJoined", null);
                    break;
                case CONFERENCE_WILL_JOIN:
                    on("onConferenceWillJoin", null);
                    break;
                case CONFERENCE_TERMINATED:
                    finish();
                    on("onConferenceTerminate", data); // intentionally uses the obsolete onConferenceLeft in order to be consistent with iOS deployment and broadcast to JS listeners
                    break;
                case READY_TO_CLOSE:
                    finish();
                    on("onConferenceLeft", null); // intentionally uses the obsolete onConferenceLeft in order to be consistent with iOS deployment and broadcast to JS listeners
                    break;
                case PARTICIPANT_JOINED:
                    on("onParticipantJoined", null);
                    break;
                case PARTICIPANT_LEFT:
                    on("onParticipantLeft", null);
                    break;
            }
        }
    }

    private void on(String name, HashMap<String, Object> data) {
        UiThreadUtil.assertOnUiThread();
        Timber.tag(TAG).d(JitsiMeetView.class.getSimpleName() + ": " + name);

        Intent intent = null;
        if (name.equals("onConferenceTerminate")) {
            if (data != null) {
                intent = new Intent(name);
                intent.putExtra("eventName", name);
            } else {
                name = "onConferenceLeft";
                intent = new Intent(name);
                intent.putExtra("eventName", name);
            }
        } else {
            intent = new Intent(name);
            intent.putExtra("eventName", name);
        }
        sendBroadcast(intent);
    }

    // The following handler is triggered when the app transitions from the background to the foreground.
    // When PIP is enabled, it can detect when the PIP window is closed by caller so it can terminate the call correctly.
    @Override
    public void onStop() {
        JitsiMeetView view = getJitsiView();
        Timber.tag(TAG).d("onStop %s", session_options.getFeatureFlags().getBoolean("pip.enabled"));
        if (session_options.getFeatureFlags().getBoolean("pip.enabled")) { //TODO: also check the CapacitorJitsiMeet's AndroidManifest.xml file and ensure android:supportsPictureInPicture="true"
            finish();
            on("onConferenceLeft", null); // intentionally uses the obsolete onConferenceLeft in order to be consistent with iOS deployment and broadcast to JS listeners
        }
        super.onStop();
    }

    // for logging entering and leaving PIP only
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        Timber.tag(TAG).d("Is in picture-in-picture mode: " + isInPictureInPictureMode);
    }

    private @Nullable JitsiMeetConferenceOptions getConferenceOptions(Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                return new JitsiMeetConferenceOptions.Builder().setRoom(uri.toString()).build();
            }
        } else if (ACTION_JITSI_MEET_CONFERENCE.equals(action)) {
            return intent.getParcelableExtra(JITSI_MEET_CONFERENCE_OPTIONS);
        }

        return null;
    }

    private static final String ADD_PEOPLE_CONTROLLER_QUERY = null;
}
