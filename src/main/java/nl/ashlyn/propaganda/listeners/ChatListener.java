package nl.ashlyn.propaganda.listeners;

import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.utils.TextScrambler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

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

        Player player = event.getPlayer();
        String message = event.getMessage();
        String prefix = "";

        if (!t1 && !t2 && LoreCommand.isLoreEnabled) {
            event.setCancelled(true);
            return;
        }

        if (!t1 && LoreCommand.isLoreEnabled || !t2 && LoreCommand.isLoreEnabled) {
            event.setMessage(TextScrambler.scrambleLetters(event.getMessage()));
        }

        if (LoreCommand.isLoreEnabled) {
            event.setFormat(ChatColor.GOLD + "[Radio] " + player.getName() + " >>> " + ChatColor.RESET + message);
        }

        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        Team opTeam = sb.getTeam("opteam");

        if (opTeam != null && opTeam.hasEntry(player.getName())) {
            prefix = opTeam.getPrefix();
        }

        event.setFormat(prefix + player.getName() + ChatColor.RESET + ": " + message);
    }
}
