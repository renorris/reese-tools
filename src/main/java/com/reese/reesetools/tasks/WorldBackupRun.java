package com.reese.reesetools.tasks;

import com.reese.reesetools.ReeseTools;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorldBackupRun extends BukkitRunnable {

    ReeseTools plugin;

    public WorldBackupRun(ReeseTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getServer().broadcastMessage("Backing up the world, expect lag...");

        String worldName = plugin.getConfig().getString("world");
        String[] worldNames = new String[3];
        worldNames[0] = worldName;
        worldNames[1] = worldName + "_nether";
        worldNames[2] = worldName + "_the_end";
        World[] worlds = new World[3];
        worlds[0] = Bukkit.getWorld(worldNames[0]);
        worlds[1] = Bukkit.getWorld(worldNames[1]);
        worlds[2] = Bukkit.getWorld(worldNames[2]);
        for (World world : worlds) {
            world.save();
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        String strDate = dateFormat.format(date);
        String filename = worldName + "_" + strDate + ".zip";

        StringBuilder zipCmd = new StringBuilder();
        zipCmd.append("zip -r " + plugin.getConfig().getString("path") + "/" + filename + " ");
        for (String w : worldNames) {
            zipCmd.append(w + "/ ");
        }

        try {
            Process p = Runtime.getRuntime().exec(zipCmd.toString(), null, Bukkit.getWorldContainer());
            this.plugin.getLogger().info("Waiting for zip command finish...");
            p.waitFor();
            this.plugin.getLogger().info("Zip cmd finished.");
        } catch (IOException | InterruptedException e) {
            plugin.getLogger().warning("Zip exception -> " + e);
        }

        Bukkit.getServer().broadcastMessage("Backup complete.");

        // Reset stored day of month for the next task run
        plugin.setStoredDayOfMonth();
    }
}
