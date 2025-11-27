package nl.ashlyn.propaganda;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import nl.ashlyn.propaganda.commands.BroadcastCommand;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import nl.ashlyn.propaganda.listeners.*;
import nl.ashlyn.propaganda.utils.Logger;
import nl.ashlyn.propaganda.voicechat.VoiceChatPlugin;
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

        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
        if (service != null) {
            VoiceChatPlugin voicechatPlugin = new VoiceChatPlugin();
            service.registerPlugin(voicechatPlugin);
            Logger.log("Successfully registered propaganda in voicechat");
        }

        Logger.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
        Logger.log(ChatColor.RED + "Propaganda Initiated!" + ChatColor.RESET + ChatColor.RED + " (Build: v" + getDescription().getVersion() + ")" + ChatColor.RESET);
        Logger.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);

    }

    @Override
    public void onDisable() {
        Logger.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
        Logger.log(ChatColor.RED + "Propaganda Disabled!" + ChatColor.RESET + ChatColor.RED + " (Build: v" + getDescription().getVersion() + ")" + ChatColor.RESET);
        Logger.log(ChatColor.RED + "+=========================================+" + ChatColor.RESET);
    }

    private void registerCommands()
    {
        registerCommand("wapenstilstand", new WapenstilstandCommand(this));
        registerCommand("broadcast", new BroadcastCommand());
        registerCommand("lore", new LoreCommand(this));
    }

    private void registerListeners()
    {
        registerListener(new ChatListener(this), this);
        registerListener(new PvPListener(this), this);
        registerListener(new PlayerJoinLeaveListener(this), this);
        registerListener(new PlayerDeathListener(this), this);
        registerListener(new PlayerInteractListener(this), this);
        registerListener(new SignChangeListener(this), this);
        registerListener(new LoreCommand(this), this);
        registerListener(new PlayerMoveListener(this), this);
    }

    private void registerCommand(String name, CommandExecutor executor)
    {
        this.getCommand(name).setExecutor(executor);
        Logger.log("Registered command: " + ChatColor.GREEN + name);
    }

    private void registerListener(Listener listener, Plugin plugin)
    {
        getServer().getPluginManager().registerEvents(listener, plugin);
        Logger.log("Registered listener: " + ChatColor.GREEN + listener.getClass().getSimpleName());
    }
}