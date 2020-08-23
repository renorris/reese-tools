package com.reese.reesetools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Snuff implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String str = String.join(" ", args);
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendTitle(str, "", 10, 80, 10);
        }
        return true;
    }
}

