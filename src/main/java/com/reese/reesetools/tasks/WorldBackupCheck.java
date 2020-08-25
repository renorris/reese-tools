package com.reese.reesetools.tasks;

import com.reese.reesetools.ReeseTools;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class WorldBackupCheck extends BukkitRunnable {

    ReeseTools plugin;

    public WorldBackupCheck(ReeseTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!plugin.getActualDayOfMonth().equals(plugin.storedDayOfMonth)) {
            int tickDelay = this.plugin.getConfig().getInt("backup-delay");
            Bukkit.getServer().broadcastMessage("Scheduling a world backup in " + tickDelay + " ticks (~" + (tickDelay / 20) + " seconds). Please prepare for lag accordingly.");
            BukkitTask backupTask = new WorldBackupRun(this.plugin).runTaskLater(this.plugin, this.plugin.getConfig().getInt("backup-delay"));
        }
        plugin.setStoredDayOfMonth();
    }
}
