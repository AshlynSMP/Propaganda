package nl.ashlyn.propaganda;

import nl.ashlyn.propaganda.commands.BroadcastCommand;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import nl.ashlyn.propaganda.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/09/2024
 */
public final class Propaganda extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.getCommand("wapenstilstand").setExecutor(new WapenstilstandCommand(this));
        this.getCommand("broadcast").setExecutor(new BroadcastCommand());
        this.getCommand("lore").setExecutor(new LoreCommand(this));

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new PvPListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new LoreCommand(this), this);

        Propaganda.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
        Propaganda.log(ChatColor.RED + "Propaganda Initiated!" + ChatColor.RESET + ChatColor.RED + " (Build: v" + getDescription().getVersion() + ")" + ChatColor.RESET);
        Propaganda.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);

    }

    @Override
    public void onDisable() {
        Propaganda.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
        Propaganda.log(ChatColor.RED + "Propaganda Disabled!" + ChatColor.RESET + ChatColor.RED + " (Build: v" + getDescription().getVersion() + ")" + ChatColor.RESET);
        Propaganda.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}