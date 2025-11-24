package nl.ashlyn.propaganda.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
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
        Block b = event.getBlock();
        Player player = event.getPlayer();

        if (b.getType().equals(Material.OAK_SIGN) ||
                b.getType().equals(Material.OAK_WALL_SIGN)) {
            String l0 = event.getLine(0);
            if (l0.equalsIgnoreCase("[radio]")) {
                String towerName = event.getLine(1).toLowerCase().trim();
                event.setLine(0, PlayerInteract.radioTower);
                event.setLine(1, ChatColor.BLUE + towerName.toUpperCase());

                boolean enabled = plugin.getConfig().getBoolean("tower-" + towerName, true);
                if (enabled) {
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.0f);
                    event.setLine(3, ChatColor.GREEN + "Online");
                } else {
                    player.playSound(player.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 1.0f, 1.0f);
                    event.setLine(3, ChatColor.RED + "Offline");
                }
            }
        }
    }
}
