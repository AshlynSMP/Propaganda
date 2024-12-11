package nl.ashlyn.propaganda.listeners;

import net.md_5.bungee.api.ChatColor;
import nl.ashlyn.propaganda.commands.WapenstilstandCommand;
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
        if (WapenstilstandCommand.isPvPDisabled && event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player attacker = (Player) event.getDamager();
            Player victim = (Player) event.getEntity();

            event.setCancelled(true);

            attacker.sendMessage(ChatColor.RED + "PvP is momenteel uitgeschakeld! Er is een wapenstilstand actief.");
            victim.sendMessage(ChatColor.GREEN + "Je bent veilig! PvP is uitgeschakeld door de wapenstilstand.");
        }
    }
}
