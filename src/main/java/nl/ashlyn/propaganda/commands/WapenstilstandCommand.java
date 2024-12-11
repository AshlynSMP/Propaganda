package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class WapenstilstandCommand implements CommandExecutor, Listener {

    private final JavaPlugin plugin;
    public static boolean isPvPDisabled;

    public WapenstilstandCommand(JavaPlugin plugin) {
        this.plugin = plugin;

        FileConfiguration config = plugin.getConfig();
        isPvPDisabled = config.getBoolean("pvp-disabled", false);
    }

    public boolean isPvPDisabled() {
        return isPvPDisabled;
    }

    public void togglePvP() {
        isPvPDisabled = !isPvPDisabled;

        plugin.getConfig().set("pvp-disabled", isPvPDisabled);
        plugin.saveConfig();
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

        togglePvP();

        if (isPvPDisabled()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "Er is een wapenstilstand opgeroepen door " + player.getName() + ". Peace and love iedereen!");
        } else {
            Bukkit.broadcastMessage(ChatColor.RED + "De wapenstilstand is opgeheven door " + player.getName() + ". Dit betekent oorlog!");
        }

        return true;
    }
}
