package com.comphenix.example;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMod extends JavaPlugin implements Listener {
	private static final int TICKS_PER_SECOND = 20;
	
	@Override
	public void onEnable() {		
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player player : getServer().getOnlinePlayers()) {
					player.sendBlockChange(player.getLocation(), Material.STONE, (byte) 0);
				}
			}
		}, 1, TICKS_PER_SECOND * 1);
	}
}
