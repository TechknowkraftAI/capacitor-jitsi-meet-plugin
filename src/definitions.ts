export interface CustomJitsiMeetPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
