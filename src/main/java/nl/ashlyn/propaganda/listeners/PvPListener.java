package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Ashlyn on 12/10/2024
 */
public class PvPListener implements Listener {

    private final JavaPlugin plugin;

    public PvPListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPvPAttempt(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        if (!(victim instanceof Player)) return;

        if (!WapenstilstandCommand.isPvPDisabled) return;

        Entity damager = event.getDamager();

        if (damager instanceof Player) {
            Player attacker = (Player) damager;
            event.setCancelled(true);
            attacker.sendMessage(ChatColor.RED + "PvP is momenteel uitgeschakeld! Er is een wapenstilstand actief.");
        }
        else if (damager instanceof Projectile) {
            Projectile projectile = (Projectile) damager;
            if (projectile.getShooter() instanceof Player) {
                Player shooter = (Player) projectile.getShooter();
                event.setCancelled(true);
                shooter.sendMessage(ChatColor.RED + "PvP is momenteel uitgeschakeld! Er is een wapenstilstand actief.");
            }
        }
    }
}