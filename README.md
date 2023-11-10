# capacitor-angular-jitsi-meet-plugin

Custom plugin for Jitsi meet in angular

## Install

```bash
npm install capacitor-angular-jitsi-meet-plugin
npx cap sync
```

## API

<docgen-index>

* [`joinConference(...)`](#joinconference)
* [`leaveConference(...)`](#leaveconference)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### joinConference(...)

```typescript
joinConference(options: { roomName: string; url: string; token?: string; channelLastN?: string; displayName?: string; subject?: string; email?: string; avatarURL?: string; startWithAudioMuted?: boolean; startWithVideoMuted?: boolean; chatEnabled?: boolean; inviteEnabled?: boolean; callIntegrationEnabled?: boolean; recordingEnabled?: boolean; liveStreamingEnabled?: boolean; screenSharingEnabled?: boolean; featureFlags?: any; configOverrides?: any; }) => Promise<{ success?: boolean; }>
```

| Param         | Type                                                                                                                                                                                                                                                                                                                                                                                                                                                      |
| ------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ roomName: string; url: string; token?: string; channelLastN?: string; displayName?: string; subject?: string; email?: string; avatarURL?: string; startWithAudioMuted?: boolean; startWithVideoMuted?: boolean; chatEnabled?: boolean; inviteEnabled?: boolean; callIntegrationEnabled?: boolean; recordingEnabled?: boolean; liveStreamingEnabled?: boolean; screenSharingEnabled?: boolean; featureFlags?: any; configOverrides?: any; }</code> |

**Returns:** <code>Promise&lt;{ success?: boolean; }&gt;</code>

--------------------


### leaveConference(...)

```typescript
leaveConference(options?: {} | undefined) => Promise<{ success?: boolean; }>
```

| Param         | Type            |
| ------------- | --------------- |
| **`options`** | <code>{}</code> |

**Returns:** <code>Promise&lt;{ success?: boolean; }&gt;</code>

--------------------

</docgen-api>
