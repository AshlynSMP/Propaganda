package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.propaganda.commands.NetherCommand;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Ashlyn on 24/10/2026
 *
 * Stops players from going into the nether while it is closed. Leaving the nether always works.
 */
public class NetherListener implements Listener {

    @EventHandler
    public void onPortal(PlayerPortalEvent event) {
        if (!NetherCommand.isNetherDisabled) return;
        if (event.getCause() != PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) return;
        if (event.getFrom().getWorld().getEnvironment() == World.Environment.NETHER) return;

        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.RED + "De nether is gesloten.");
    }
}
