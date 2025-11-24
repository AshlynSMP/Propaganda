package nl.ashlyn.propaganda.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  Created by Ashlyn on 24/11/2025
 */
public class PlayerInteract implements Listener {

    private final JavaPlugin plugin;

    public PlayerInteract(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public static String radioTower = ChatColor.GOLD + "[Radio Toren]";

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (action.equals(Action.LEFT_CLICK_BLOCK) && event.getClickedBlock() != null) {
            Material type = event.getClickedBlock().getType();
            if (type.equals(Material.OAK_SIGN) || type.equals(Material.OAK_WALL_SIGN)) {
                Sign s = (Sign) event.getClickedBlock().getState();
                if (s.getLine(0).equals(radioTower)) {
                    String towerName = ChatColor.stripColor(s.getLine(1)).toLowerCase().trim();

                    boolean online = plugin.getConfig().getBoolean("tower-" + towerName, true);
                    online = !online;
                    plugin.getConfig().set("tower-" + towerName, online);
                    plugin.saveConfig();

                    if (online) {
                        s.setLine(3, ChatColor.GREEN + "Online");
                        player.sendMessage(ChatColor.GREEN + "Radio toren " + towerName + " is nu online.");
                    } else {
                        s.setLine(3, ChatColor.RED + "Offline");
                        player.sendMessage(ChatColor.RED + "Radio toren " + towerName + " is nu offline.");
                    }
                    s.update(true);

                    boolean t1 = plugin.getConfig().getBoolean("tower-oost", true);
                    boolean t2 = plugin.getConfig().getBoolean("tower-west", true);
                    if (!t1 && !t2) {
                        plugin.getServer().broadcastMessage(ChatColor.RED + "Both radio towers are offline.");
                        for (Player p : plugin.getServer().getOnlinePlayers()) {
                            p.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 1.0f);
                        }
                    }
                }
            }
        }
    }
}
