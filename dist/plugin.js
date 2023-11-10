var capacitorCustomJitsiMeet = (function (exports, core) {
    'use strict';

    const CustomJitsiMeet$1 = core.registerPlugin('CustomJitsiMeet', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.CustomJitsiMeetWeb()),
    });

    class CustomJitsiMeetWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        CustomJitsiMeetWeb: CustomJitsiMeetWeb,
        CustomJitsiMeet: CustomJitsiMeet
    });

    exports.CustomJitsiMeet = CustomJitsiMeet$1;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map
