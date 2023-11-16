import { registerPlugin } from '@capacitor/core';
const CustomJitsiMeet = registerPlugin('CustomJitsiMeet', {
    web: () => import('./web').then(m => new m.CustomJitsiMeetWeb()),
});
export * from './definitions';
export { CustomJitsiMeet };
//# sourceMappingURL=index.js.map