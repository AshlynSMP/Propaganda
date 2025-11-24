package nl.ashlyn.propaganda.listeners;

import nl.ashlyn.propaganda.commands.LoreCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 11/24/2025
 */
public class ChatListener implements Listener {

    private final JavaPlugin plugin;

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        boolean t1 = plugin.getConfig().getBoolean("tower-oost", true);
        boolean t2 = plugin.getConfig().getBoolean("tower-west", true);
        if (!t1 && !t2 && LoreCommand.isLoreEnabled) {
            event.setCancelled(true);
            return;
        }

        Player player = event.getPlayer();
        String message = event.getMessage();

        if (LoreCommand.isLoreEnabled) {
            event.setFormat(ChatColor.GOLD + "[Radio] " + player.getName() + " >>> " + ChatColor.RESET + message);
        }
    }
}
