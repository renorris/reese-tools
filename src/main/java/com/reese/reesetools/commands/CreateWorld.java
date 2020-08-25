package com.reese.reesetools.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CreateWorld implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String worldName = args[0];
        WorldCreator wc = new WorldCreator(worldName);
        wc.environment(World.Environment.NORMAL);
        wc.type(WorldType.NORMAL);
        wc.createWorld();
        return true;
    }
}

