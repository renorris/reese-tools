package com.reese.reesetools;

import com.reese.reesetools.commands.*;
import com.reese.reesetools.tasks.WorldBackupCheck;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Logger;

public class ReeseTools extends JavaPlugin {

    public static ReeseTools plugin;
    public Logger logger = this.getLogger();
    public Integer storedDayOfMonth;

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();

        // Set current day of month initially
        setStoredDayOfMonth();

        this.getCommand("bloc").setExecutor(new Bloc());
        this.getCommand("snuff").setExecutor(new Snuff());
        this.getCommand("repair").setExecutor(new RepairItem());
        this.getCommand("createworld").setExecutor(new CreateWorld());
        this.getCommand("teleport").setExecutor(new TeleportToWorld());
        this.getCommand("worldbackup").setExecutor(new ManualWorldBackup(this));

        BukkitTask backupCheckTask = new WorldBackupCheck(this.plugin).runTaskTimer(this.plugin, 20, 200);
    }

    @Override
    public void onDisable() {
        // bruh
    }

    public void setStoredDayOfMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
        this.storedDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    }

    public Integer getActualDayOfMonth() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
        return cal.get(Calendar.DAY_OF_MONTH);
    }
}
