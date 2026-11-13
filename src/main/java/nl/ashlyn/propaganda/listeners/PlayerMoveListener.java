package nl.ashlyn.propaganda.listeners;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

/**
 *  Created by Ashlyn on 27/11/2025
 */
public class PlayerMoveListener implements Listener {

    private final JavaPlugin plugin;
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public PlayerMoveListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        cooldowns.remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Location to = event.getTo();
        if (to == null || (event.getFrom().getBlockX() == to.getBlockX() && event.getFrom().getBlockZ() == to.getBlockZ())) {
            return;
        }

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        long current = System.currentTimeMillis();
        long last = cooldowns.getOrDefault(uuid, 0L);

        if (current - last < 5000) {
            return;
        }

        World world = player.getWorld();
        Location loc = player.getLocation();

        double borderSize = world.getWorldBorder().getSize() / 2.0;
        double borderX = world.getWorldBorder().getCenter().getX();
        double borderZ = world.getWorldBorder().getCenter().getZ();
        double warning = world.getWorldBorder().getWarningDistance();

        double minX = borderX - borderSize + warning;
        double maxX = borderX + borderSize - warning;
        double minZ = borderZ - borderSize + warning;
        double maxZ = borderZ + borderSize - warning;

        double newX = loc.getX();
        double newZ = loc.getZ();
        boolean teleported = false;

        if (loc.getX() >= maxX) {
            newX = minX;
            teleported = true;
        } else if (loc.getX() <= minX) {
            newX = maxX;
            teleported = true;
        }

        if (loc.getZ() >= maxZ) {
            newZ = minZ;
            teleported = true;
        } else if (loc.getZ() <= minZ) {
            newZ = maxZ;
            teleported = true;
        }

        if (teleported) {
            cooldowns.put(uuid, System.currentTimeMillis());

            Location newLocation = new Location(
                    world,
                    newX,
                    world.getHighestBlockYAt((int) newX, (int) newZ),
                    newZ,
                    loc.getYaw(),
                    loc.getPitch()
            );

            player.teleport(newLocation);
        }
    }
}