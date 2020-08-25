package com.reese.reesetools.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Broadcast player location to the server

public class Bloc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = Bukkit.getPlayer(sender.getName());
            Location location = player.getLocation();
            World.Environment environment = player.getWorld().getEnvironment();
            int x = (int) Math.round(location.getX());
            int y = (int) Math.round(location.getY());
            int z = (int) Math.round(location.getZ());
            StringBuilder str = new StringBuilder();
            str.append("Location for ");
            str.append(ChatColor.BOLD);
            str.append(player.getName());
            str.append(ChatColor.RESET);
            str.append(" ");

            str.append(ChatColor.AQUA);
            str.append(x);
            str.append(" ");
            str.append(y);
            str.append(" ");
            str.append(z);
            str.append(ChatColor.RESET);

            str.append(" ");
            if (environment == World.Environment.NORMAL) {
                str.append(ChatColor.GREEN);
                str.append("OVERWORLD");
            }
            else if (environment == World.Environment.NETHER) {
                str.append(ChatColor.DARK_RED);
                str.append("NETHER");
            }
            else if (environment == World.Environment.THE_END) {
                str.append(ChatColor.DARK_GRAY);
                str.append("THE END");
            }
            str.append(ChatColor.RESET);

            String finalStr = str.toString();
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendMessage(finalStr);
            }
        }
        else {
            sender.sendMessage("This command is only runnable from a player");
        }
        return true;
    }
}
