package nl.ashlyn.propaganda.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 *  Created by Ashlyn on 26/11/2025
 */
public class Logger {

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[Propaganda] " + ChatColor.GOLD + message);
    }
}
