import { WebPlugin } from '@capacitor/core';

import type { CustomJitsiMeetPlugin } from './definitions';

export class CustomJitsiMeetWeb extends WebPlugin implements CustomJitsiMeetPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
