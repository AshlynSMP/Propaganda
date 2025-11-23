package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class LoreCommand implements CommandExecutor, Listener {

    private final JavaPlugin plugin;
    public static boolean isLoreEnabled;

    public LoreCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();
        isLoreEnabled = config.getBoolean("lore-enabled", false);
    }

    public boolean isLoreEnabled() {
        return isLoreEnabled;
    }

    public void toggleLore() {
        isLoreEnabled = !isLoreEnabled;

        plugin.getConfig().set("lore-enabled", isLoreEnabled);
        plugin.saveConfig();

        for (Player player : Bukkit.getOnlinePlayers()) {
            updatePlayerDisplayName(player);
        }
    }

    public static void updatePlayerDisplayName(Player player) {
        if (player.isOp()) {
            return;
        }

        if (isLoreEnabled) {
            String modifiedName = ChatColor.GOLD + "c!" + ChatColor.GOLD + player.getName();
            player.setDisplayName(modifiedName);
            player.setPlayerListName(modifiedName);

            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Team team = scoreboard.getTeam("lorePrefix");
            if (team == null) {
                team = scoreboard.registerNewTeam("lorePrefix");
                team.setPrefix(ChatColor.GOLD + "c!" + ChatColor.GOLD);
            }
            team.addEntry(player.getName());
        } else {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());

            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Team team = scoreboard.getTeam("lorePrefix");
            if (team != null) {
                team.removeEntry(player.getName());
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.getName().equalsIgnoreCase("ntnt") && !player.getName().equalsIgnoreCase("TheBigCheese_420") && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "Deze command kan alleen gebruikt worden door de leiders.");
            return true;
        }

        toggleLore();

        if (isLoreEnabled()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Lore mode is geactiveerd door " + player.getName() + " zorg ervoor dat je de proximity chat gebruikt.");
            for (Player allPlayers: Bukkit.getOnlinePlayers()){
                allPlayers.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
            }
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "Lore mode is uitgezet door " + player.getName());
        }

        return true;
    }
}