package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.core.api.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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
            // Shadow color not in sendTitle
            plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title " + player.getName() + " title {\"text\":\"氏\uDAC0\uDC00\uDAC0\uDC00\uDAC0\uDC00\uDAFF\uDFFF氐\",\"shadow_color\":false}");
        }

        event.setJoinMessage(ChatColor.GREEN + "[+] " + player.getName());

        if (CoreConfig.isLoreEnabled()) {
            player.sendMessage(ChatColor.RED + "+=========================================+");
            player.sendMessage(ChatColor.RED + "Lore mode is actief. Blijf in je rol!");
            player.sendMessage(ChatColor.RED + "+=========================================+");
        }

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team opTeam = sb.getTeam("opteam");

        if (opTeam == null) {
            opTeam = sb.registerNewTeam("opteam");
            opTeam.setPrefix("ん ");
        }

        if (player.isOp()) {
            if (!opTeam.getEntries().contains(player.getName())) {
                opTeam.addEntry(player.getName());
            }
        } else {
            opTeam.removeEntry(player.getName());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(ChatColor.RED + "[-] " + player.getName());
    }
}
