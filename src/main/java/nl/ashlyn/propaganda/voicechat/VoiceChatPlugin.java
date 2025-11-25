package nl.ashlyn.propaganda.voicechat;

import de.maxhenkel.voicechat.api.Group;
import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import nl.ashlyn.propaganda.Propaganda;

/**
 *  Created by Ashlyn on 25/11/2025
 */
public class VoiceChatPlugin implements VoicechatPlugin {

    private static VoicechatServerApi api;

    public static VoicechatServerApi getApi() {
        return api;
    }

    @Override
    public String getPluginId() {
        return Propaganda.PLUGIN_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        VoiceChatPlugin.api = (VoicechatServerApi) api;
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoicechatServerStartedEvent.class, this::onServerStarted, 100);
    }

    public void onServerStarted(VoicechatServerStartedEvent event) {
        VoicechatServerApi api = event.getVoicechat();

        String[] channels = {
                "5.44 MHz",
                "7.52 MHz",
                "9.73 MHz",
                "12.15 MHz",
                "17.20 MHz"
        };

        for (String name : channels) {
            Group group = api.groupBuilder()
                    .setPersistent(true)
                    .setName(name)
                    .setType(Group.Type.ISOLATED)
                    .build();

            Propaganda.log("Created voicechat group: " + group.getName());
        }
    }
}