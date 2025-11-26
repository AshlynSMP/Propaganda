package nl.ashlyn.propaganda.voicechat;

import de.maxhenkel.voicechat.api.*;
import de.maxhenkel.voicechat.api.events.CreateGroupEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import nl.ashlyn.propaganda.Propaganda;
import nl.ashlyn.propaganda.utils.Logger;

import java.util.Arrays;

/**
 *  Created by Ashlyn on 25/11/2025
 */
public class VoiceChatPlugin implements VoicechatPlugin {

    private static VoicechatServerApi api;

    public static String[] channels = {
            "5.44 MHz",
            "7.52 MHz",
            "9.73 MHz",
            "12.15 MHz",
            "17.20 MHz"
    };

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
        registration.registerEvent(CreateGroupEvent.class, this::onGroupCreated, 100);
    }

    public void onServerStarted(VoicechatServerStartedEvent event) {
        VoicechatServerApi api = event.getVoicechat();

        for (String name : channels) {
            Group group = api.groupBuilder()
                    .setPersistent(true)
                    .setName(name)
                    .setType(Group.Type.NORMAL)
                    .build();

            Logger.log("Created voicechat group: " + group.getName());
        }
    }

    public void onGroupCreated(CreateGroupEvent event) {
        String groupName = event.getGroup().getName();

        if (!Arrays.asList(channels).contains(groupName)) {
            event.cancel();
            Logger.log("Cancelled creation of unauthorized group: " + groupName);
        }
    }
}