package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class LoreCommand implements CommandExecutor, Listener {

    private final JavaPlugin plugin;
    public static boolean isLoreEnabled;
    public static String[] leaders = {};

    public LoreCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();
        isLoreEnabled = config.getBoolean("lore-enabled", false);
        leaders = config.getStringList("leaders").toArray(new String[0]);
    }

    public boolean isLoreEnabled() {
        return isLoreEnabled;
    }

    public void toggleLore() {
        isLoreEnabled = !isLoreEnabled;

        plugin.getConfig().set("lore-enabled", isLoreEnabled);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        boolean isLeader = Arrays.asList(leaders).contains(player.getName());
        if (!isLeader && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "Deze command kan alleen gebruikt worden door de leiders.");
            return true;
        }

        toggleLore();

        if (isLoreEnabled()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Lore mode is geactiveerd door " + player.getName() + " zorg ervoor dat je de proximity chat gebruikt.");
            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                allPlayers.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
            }

            Bukkit.getScheduler().runTaskTimer(plugin, () -> {
                for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                    allPlayers.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "Lore mode staat aan."));
                }
            }, 0L, 20L);

        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "Lore mode is uitgezet door " + player.getName());
        }

        return true;
    }
}