package nl.ashlyn.propaganda.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *  Created by Ashlyn on 24/11/2025
 */
public class SignChange implements Listener {

    private final JavaPlugin plugin;

    public SignChange(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        Block b = event.getBlock();
        if (b.getType().equals(Material.OAK_SIGN) ||
                b.getType().equals(Material.OAK_WALL_SIGN)) {
            String l0 = event.getLine(0);
            if (l0.equalsIgnoreCase("[radio]")) {
                String towerNumber = event.getLine(1).toLowerCase().trim();
                event.setLine(0, PlayerInteract.radioTower);

                boolean enabled = plugin.getConfig().getBoolean("tower-" + towerNumber, true);
                if (enabled) {
                    event.setLine(1, ChatColor.GREEN + "Online");
                } else {
                    event.setLine(1, ChatColor.RED + "Offline");
                }

                // store the tower id on line 2
                event.setLine(2, towerNumber);
            }
        }
    }
}
