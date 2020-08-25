package com.reese.reesetools.commands;

import com.reese.reesetools.ReeseTools;
import com.reese.reesetools.tasks.WorldBackupRun;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;

public class ManualWorldBackup implements CommandExecutor {

    ReeseTools plugin;

    public ManualWorldBackup(ReeseTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Scheduling WorldBackupRun...");
        BukkitTask backupTask = new WorldBackupRun(this.plugin).runTask(this.plugin);
        return true;
    }
}
