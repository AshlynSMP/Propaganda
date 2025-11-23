package nl.ashlyn.propaganda.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

public class ChatListener implements Listener {

    private final JavaPlugin plugin;

    public ChatListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String prefix = "";

        if (player.getScoreboard() != null) {
            Team team = player.getScoreboard().getEntryTeam(player.getName());
            if (team != null) {
                prefix = team.getPrefix();
            }
        }

        event.setFormat(prefix + player.getName() + ChatColor.RESET + ": " + message);
    }
}
