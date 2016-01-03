package com.destroystokyo.mapcloneblock;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
    private final String PERM_BYPASS = "mapcloneblock.bypass";

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        if (event.getRecipe().getResult().getType() == Material.EMPTY_MAP) { // For w/e reason, the result is an empty map
            for (ItemStack itemStack : event.getInventory().getMatrix()) {
                if (itemStack != null && itemStack.getType() == Material.EMPTY_MAP) {
                    for (HumanEntity human : event.getViewers()) {
                        if (!human.hasPermission(PERM_BYPASS)) {
                            event.getInventory().setResult(new ItemStack(Material.AIR));
                            break;
                        }
                    }
                }
            }
        }
    }
}
