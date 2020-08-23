package com.reese.reesetools;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WorldBackup extends BukkitRunnable {

    ReeseTools plugin;

    public WorldBackup(ReeseTools plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!plugin.getActualDayOfMonth().equals(plugin.storedDayOfMonth)) {
            // It seems midnight has passed and a backup is required.
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
                Runtime.getRuntime().exec(zipCmd.toString(), null, Bukkit.getWorldContainer());
            } catch (IOException e) {
                plugin.getLogger().warning("Zip exception -> " + e);
            }

            Bukkit.getServer().broadcastMessage("Backup complete.");
        }

        // Reset stored day of month for the next task run
        plugin.setStoredDayOfMonth();
    }
}
