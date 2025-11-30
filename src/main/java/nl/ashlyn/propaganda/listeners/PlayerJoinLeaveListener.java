package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.utils.Logger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class PlayerJoinLeaveListener implements Listener {

    private final JavaPlugin plugin;

    public PlayerJoinLeaveListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String[] welcomeMessage = {
                "",
                "",
                "",
                "ッ",
                "",
                ChatColor.LIGHT_PURPLE + "  Welkom terug " + ChatColor.YELLOW + player.getName(),
        };

        if(player.hasPlayedBefore()) {
            player.sendMessage(welcomeMessage);
        } else {
            player.sendTitle(ChatColor.DARK_PURPLE + "Ashlyn SMP Title", ChatColor.LIGHT_PURPLE + "If needed extra info goes here", 10, 70, 20);
        }

        event.setJoinMessage(ChatColor.GREEN + "[+] " + player.getName());

        if (LoreCommand.isLoreEnabled) {
            player.sendMessage(ChatColor.RED + "+=========================================+");
            player.sendMessage(ChatColor.RED + "Lore mode is actief. Blijf in je rol!");
            player.sendMessage(ChatColor.RED + "+=========================================+");
        }

        boolean t1 = plugin.getConfig().getBoolean("tower-oost", true);
        boolean t2 = plugin.getConfig().getBoolean("tower-west", true);

        if (!t1 && !t2 && LoreCommand.isLoreEnabled) {
            player.sendMessage(ChatColor.RED + "Allebei de radio torens zijn offline! Je kunt de radio nu niet gebruiken!");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.RED + "[-] " + player.getName());
    }
}
