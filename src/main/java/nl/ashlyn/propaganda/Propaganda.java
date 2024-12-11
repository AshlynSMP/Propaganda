package nl.ashlyn.propaganda;

import nl.ashlyn.propaganda.commands.BroadcastCommand;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.commands.PluginsCommand;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import nl.ashlyn.propaganda.listeners.PlayerJoinListener;
import nl.ashlyn.propaganda.listeners.PvPListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/09/2024
 */
public final class Propaganda extends JavaPlugin {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";

    @Override
    public void onEnable() {

        saveDefaultConfig();

        this.getCommand("plugins").setExecutor(new PluginsCommand());
        this.getCommand("wapenstilstand").setExecutor(new WapenstilstandCommand(this));
        this.getCommand("broadcast").setExecutor(new BroadcastCommand());
        this.getCommand("lore").setExecutor(new LoreCommand(this));

        getServer().getPluginManager().registerEvents(new PvPListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new LoreCommand(this), this);

        Propaganda.log(RED + "+=========================================+" + RESET);
        Propaganda.log(RED + "Propaganda Initiated!" + RESET + RED + " (Build: " + getDescription().getVersion() + ")" + RESET);
        Propaganda.log(RED + "+=========================================+" + RESET);

    }

    @Override
    public void onDisable() {
        Propaganda.log(RED + "+=========================================+" + RESET);
        Propaganda.log(RED + "Propaganda Disabled!" + RESET + RED + " (Build: " + getDescription().getVersion() + ")" + RESET);
        Propaganda.log(RED + "+=========================================+" + RESET);
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}