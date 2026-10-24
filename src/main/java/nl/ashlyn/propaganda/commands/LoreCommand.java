package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.ashlyn.core.api.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class LoreCommand implements CommandExecutor, Listener {

    private final JavaPlugin plugin;
    private BukkitRunnable bukkitRunnable;

    public LoreCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void toggleLore() {
        CoreConfig.setLoreEnabled(!CoreConfig.isLoreEnabled());
        CoreConfig.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (!CoreConfig.isLeader(player.getName()) && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "Deze command kan alleen gebruikt worden door de leiders.");
            return true;
        }

        toggleLore();

        if (CoreConfig.isLoreEnabled()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Lore mode is geactiveerd door " + player.getName() + " zorg ervoor dat je de proximity chat gebruikt.");
            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                // Shadow color not in sendTitle
                plugin.getServer().dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"ひ\uDAC0\uDC00\uDAC0\uDC00\uDAC0\uDC00\uDAFF\uDFFFゎ\",\"shadow_color\":false}");
                allPlayers.playSound(allPlayers.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
            }

            bukkitRunnable = new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.spigot().sendMessage(
                                ChatMessageType.ACTION_BAR,
                                new TextComponent(ChatColor.GREEN + "Lore mode staat aan.")
                        );
                    }
                }
            };

            bukkitRunnable.runTaskTimer(plugin, 0L, 20L);

        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "Lore mode is uitgezet door " + player.getName());

            if (bukkitRunnable != null) {
                bukkitRunnable.cancel();
                bukkitRunnable = null;
            }
        }

        return true;
    }
}