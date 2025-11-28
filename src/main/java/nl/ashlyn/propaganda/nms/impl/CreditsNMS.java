package nl.ashlyn.propaganda.nms.impl;

import com.comphenix.protocol.PacketType.Play.Server;
import com.comphenix.protocol.events.PacketContainer;
import nl.ashlyn.propaganda.nms.Credits;
import nl.ashlyn.propaganda.nms.ICredits;
import nl.ashlyn.propaganda.utils.Logger;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 *  Created by Ashlyn on 28/11/2025
 *  Originally by LoneDev on SpigotMC but updated to work with newer versions
 *  https://www.spigotmc.org/resources/end-credits.90026/
 */
public class CreditsNMS implements ICredits {
    private static Field field_spectatorMode = null;

    @Override
    public void showCredits(Player player) {
        if (field_spectatorMode == null) {
            Logger.log("Failed to show credits screen to player. This server version is not compatible.");
            return;
        }

        PacketContainer packet = new PacketContainer(Server.GAME_STATE_CHANGE);
        try {
            Object spectatorMode = field_spectatorMode.get(null);
            packet.getModifier().write(0, spectatorMode);
            packet.getFloat().write(0, 1.0F);
            Credits.sendPacket(player, packet);

            Logger.log("Sent credits packet to " + player.getName());
        } catch (IllegalAccessException e) {
            Logger.log("Failed to show credits screen to player:");
            e.printStackTrace();
        }
    }

    static {
        try {
            Field eField = Server.GAME_STATE_CHANGE.getPacketClass().getDeclaredField("e");
            eField.setAccessible(true);
            Class<?> valueType = eField.getType();

            try {
                Field win = valueType.getDeclaredField("WIN_GAME");
                if (Modifier.isStatic(win.getModifiers()) && win.getType().equals(valueType)) {
                    win.setAccessible(true);
                    field_spectatorMode = win;
                }
            } catch (NoSuchFieldException e) {
                Class<?> declaring = valueType.getDeclaringClass();
                if (declaring != null) {
                    try {
                        Field win = declaring.getDeclaredField("WIN_GAME");
                        if (Modifier.isStatic(win.getModifiers()) && win.getType().equals(valueType)) {
                            win.setAccessible(true);
                            field_spectatorMode = win;
                        }
                    } catch (NoSuchFieldException err) {
                        Logger.log("Failed to show credits screen to player:");
                        err.printStackTrace();
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            Logger.log("Failed to initialize the plugin. This server version is not compatible.");
            e.printStackTrace();
        }
    }
}
