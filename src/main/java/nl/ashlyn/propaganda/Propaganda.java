package nl.ashlyn.propaganda;

import nl.ashlyn.core.utils.Logger;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.commands.NetherCommand;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import nl.ashlyn.propaganda.listeners.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Ashlyn on 12/09/2024
 */
public final class Propaganda extends JavaPlugin {

    public static final String PLUGIN_ID = "Propaganda";

    @Override
    public void onEnable() {

        saveDefaultConfig();
        registerCommands();
        registerListeners();

        Logger.logPropaganda("+=========================================+");
        Logger.logPropaganda("Propaganda Initiated! (Build: v" + getDescription().getVersion() + ")");
        Logger.logPropaganda("+=========================================+");

    }

    @Override
    public void onDisable() {
        Logger.logPropaganda("+=========================================+");
        Logger.logPropaganda("Propaganda Disabled! (Build: v" + getDescription().getVersion() + ")");
        Logger.logPropaganda("+=========================================+");
    }

    private void registerCommands()
    {
        registerCommand("wapenstilstand", new WapenstilstandCommand(this));
        registerCommand("lore", new LoreCommand(this));
        registerCommand("nether", new NetherCommand(this));
    }

    private void registerListeners()
    {
        registerListener(new PvPListener(this), this);
        registerListener(new PlayerJoinLeaveListener(this), this);
        registerListener(new PlayerDeathListener(this), this);
        registerListener(new PlayerMoveListener(this), this);
        registerListener(new NetherListener(), this);
    }

    private void registerCommand(String name, CommandExecutor executor)
    {
        this.getCommand(name).setExecutor(executor);
        Logger.logPropaganda("Registered command: " + ChatColor.GREEN + name);
    }

    private void registerListener(Listener listener, Plugin plugin)
    {
        getServer().getPluginManager().registerEvents(listener, plugin);
        Logger.logPropaganda("Registered listener: " + ChatColor.GREEN + listener.getClass().getSimpleName());
    }
}