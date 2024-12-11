package nl.ashlyn.propaganda.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

/**
 * Created by Ashlyn on 12/09/2024
 */
public class PluginsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            StringBuilder pluginList = new StringBuilder();

            for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                String[] authors = plugin.getDescription().getAuthors().toArray(new String[0]);
                for (String author : authors) {
                    if (author.equalsIgnoreCase("Ashlyn")) {
                        pluginList.append(ChatColor.GREEN)
                                .append(plugin.getName())
                                .append(" (Version: ")
                                .append(plugin.getDescription().getVersion())
                                .append(")\n");
                    }
                }
            }

            if (pluginList.length() == 0) {
                sender.sendMessage("No plugins by Ashlyn found.");
            } else {
                sender.sendMessage(ChatColor.GOLD + "Plugins by Ashlyn:\n" + pluginList);
            }

            return true;
    }
}
