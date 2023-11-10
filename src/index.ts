import { registerPlugin } from '@capacitor/core';

import type { CustomJitsiMeetPlugin } from './definitions';

const CustomJitsiMeet = registerPlugin<CustomJitsiMeetPlugin>('CustomJitsiMeet', {
  web: () => import('./web').then(m => new m.CustomJitsiMeetWeb()),
});

export * from './definitions';
export { CustomJitsiMeet };
