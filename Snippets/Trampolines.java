package me.JessHilario.Trampolines;

import me.JessHilario.TitleTabList.Commands.sendActionBar;
import me.JessHilario.TitleTabList.Commands.sendTitleMessage;
import me.JessHilario.TitleTabList.Listeners.onTabTitle;
import me.JessHilario.TitleTabList.Listeners.onTitleMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{

	public void onEnable(){
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(this, this);
	}
	
  @EventHandler
    public void onTrampoline(PlayerMoveEvent event){
        Player p = event.getPlayer();
        Vector v = event.getPlayer().getLocation().getDirection();
        if(p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.GOLD_PLATE){
            p.setVelocity(new Vector(v.multiply(2));
            // p.setVelocity(new Vector(p.getVelocity().getX(), 2, p.getVelocity().getZ()));
        }
    }
}
