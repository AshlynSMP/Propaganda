package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class BroadcastCommand implements CommandExecutor {

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

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /bc <message>");
            return false;
        }

        String message = String.join(" ", args);

        Bukkit.broadcastMessage(ChatColor.GOLD + "[Radio] " + ChatColor.GOLD + message);
        return true;
    }
}