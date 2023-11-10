import { WebPlugin } from '@capacitor/core';
import type { CustomJitsiMeetPlugin } from './definitions';
export declare class CustomJitsiMeetWeb extends WebPlugin implements CustomJitsiMeetPlugin {
    joinConference(options: {
        roomName: string;
        url: string;
        token?: string;
        channelLastN?: string;
        displayName?: string;
        subject?: string;
        email?: string;
        avatarURL?: string;
        startWithAudioMuted?: boolean;
        startWithVideoMuted?: boolean;
        chatEnabled?: boolean;
        inviteEnabled?: boolean;
        callIntegrationEnabled?: boolean;
        recordingEnabled?: boolean;
        liveStreamingEnabled?: boolean;
        screenSharingEnabled?: boolean;
        featureFlags?: any;
        configOverrides?: any;
    }): Promise<{
        success?: boolean;
    }>;
    leaveConference(options?: {}): Promise<{
        success?: boolean;
    }>;
}
declare const CustomJitsiMeet: CustomJitsiMeetWeb;
export { CustomJitsiMeet };
