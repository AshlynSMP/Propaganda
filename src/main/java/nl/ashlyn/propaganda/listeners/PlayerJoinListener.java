package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.propaganda.commands.LoreCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class PlayerJoinListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerJoinListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LoreCommand.updatePlayerDisplayName(player);

        if (LoreCommand.isLoreEnabled) {
            player.sendMessage(ChatColor.RED + "+=========================================+");
            player.sendMessage(ChatColor.RED + "Lore mode is actief. Blijf in je rol!");
            player.sendMessage(ChatColor.RED + "+=========================================+");
        }
    }
}
