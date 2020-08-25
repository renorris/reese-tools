package com.reese.reesetools.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class RepairItem implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ItemStack heldItem = Bukkit.getPlayer(sender.getName()).getInventory().getItemInMainHand();
        Damageable itemMeta = (Damageable) heldItem.getItemMeta();
        itemMeta.setDamage(0);
        heldItem.setItemMeta((ItemMeta) itemMeta);
        return true;
    }
}
