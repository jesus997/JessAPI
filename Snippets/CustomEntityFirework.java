package me.NerdsWBNerds.CustomEntityFirework;

import net.minecraft.server.v1_7_R3.EntityFireworks;
import net.minecraft.server.v1_7_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_7_R3.World;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftPlayer;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

/**
*
* Get firework builder
* FireworkEffect.Builder builder = FireworkEffect.builder();
*
* Create a firework effect with the builder
* FireworkEffect effect = builder.flicker(false).trail(false).with(FireworkEffect.Type.BALL)withColor(Color.RED).withFade(Color.BLUE).build();
* 
* Spawn our firework
* CustomEntityFirework.spawn(location, effect);
*
* Only show it to player player1, player2, and player3
* CustomEntityFirework.spawn(location, effect, player1, player2, player3);
*
**/

public class CustomEntityFirework extends EntityFireworks {
    Player[] players = null;
 
    public CustomEntityFirework(World world, Player... p) {
        super(world);
        players = p;
        this.a(0.25F, 0.25F);
    }
 
    boolean gone = false;
 
    @Override
    public void h() {
        if (gone) {
            return;
        }
 
        if (!this.world.isStatic) {
            gone = true;
 
            if (players != null) {
                if (players.length > 0) {
                    for (Player player : players) {
                        (((CraftPlayer) player).getHandle()).playerConnection.sendPacket(new PacketPlayOutEntityStatus(this, (byte) 17));
                    }
 
                    this.die();
                    return;
                }
            }
 
            world.broadcastEntityEffect(this, (byte) 17);
            this.die();
        }
    }
 
    public static void spawn(Location location, FireworkEffect effect, Player... players) {
        try {
            CustomEntityFirework firework = new CustomEntityFirework(((CraftWorld) location.getWorld()).getHandle(), players);
            FireworkMeta meta = ((Firework) firework.getBukkitEntity()).getFireworkMeta();
            meta.addEffect(effect);
            ((Firework) firework.getBukkitEntity()).setFireworkMeta(meta);
            firework.setPosition(location.getX(), location.getY(), location.getZ());
 
            if ((((CraftWorld) location.getWorld()).getHandle()).addEntity(firework)) {
                firework.setInvisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}