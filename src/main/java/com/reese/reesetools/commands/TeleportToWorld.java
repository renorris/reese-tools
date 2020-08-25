package com.reese.reesetools.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportToWorld implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String worldName = args[0];
        Player player = Bukkit.getPlayer(sender.getName());
        World world = Bukkit.getServer().getWorld(worldName);
        Location location = new Location(world, 0.0, 100.0, 0.0);
        player.teleport(location);
        return true;
    }
}

