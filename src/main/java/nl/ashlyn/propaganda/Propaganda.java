package nl.ashlyn.propaganda;

import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import nl.ashlyn.propaganda.commands.BroadcastCommand;
import nl.ashlyn.propaganda.commands.LoreCommand;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import nl.ashlyn.propaganda.listeners.*;
import nl.ashlyn.propaganda.voicechat.VoiceChatPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;

/**
 * Created by Ashlyn on 12/09/2024
 */
public final class Propaganda extends JavaPlugin {

    public static String PLUGIN_ID = "Propaganda";

    @Nullable
    private VoiceChatPlugin voicechatPlugin;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);
        if (service != null) {
            voicechatPlugin = new VoiceChatPlugin();
            service.registerPlugin(voicechatPlugin);
            Propaganda.log("Successfully registered propaganda in voicechat");
        }

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
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Propaganda] " + ChatColor.RESET + message);
    }
}