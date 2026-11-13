package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.core.api.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 24/10/2026
 */
public class NetherCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    public static boolean isNetherDisabled;

    public NetherCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        isNetherDisabled = plugin.getConfig().getBoolean("nether-disabled", false);
    }

    public void toggleNether() {
        isNetherDisabled = !isNetherDisabled;

        plugin.getConfig().set("nether-disabled", isNetherDisabled);
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player && !CoreConfig.isLeader(player.getName()) && !player.isOp()) {
            player.sendMessage(ChatColor.RED + "Deze command kan alleen gebruikt worden door de leiders.");
            return true;
        }

        toggleNether();

        if (isNetherDisabled) {
            Bukkit.broadcastMessage(ChatColor.RED + "De nether is gesloten door " + sender.getName() + ".");
        } else {
            Bukkit.broadcastMessage(ChatColor.GREEN + "De nether is weer open, geopend door " + sender.getName() + ".");
        }

        return true;
    }
}
