import { WebPlugin } from '@capacitor/core';
export class CustomJitsiMeetWeb extends WebPlugin {
    // @ts-ignore
    async joinConference(options) {
        throw this.unavailable('the web implementation is not available. Please use Jitsi Meet API to implement Jitsi in web app');
    }
    ;
    // @ts-ignore
    async leaveConference(options) {
        throw this.unavailable('the web implementation is not available. Please use Jitsi Meet API to implement Jitsi in web app');
    }
    ;
}
const CustomJitsiMeet = new CustomJitsiMeetWeb();
export { CustomJitsiMeet };
//# sourceMappingURL=web.js.map