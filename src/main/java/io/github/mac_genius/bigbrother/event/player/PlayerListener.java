package io.github.mac_genius.bigbrother.event.player;

import io.github.mac_genius.bigbrother.BigBrother;
import io.github.mac_genius.bigbrother.PlayerConSniffer;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Field;

/**
 * Created by Mac on 3/17/2016.
 */
public class PlayerListener implements Listener {

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        EntityPlayer player1 = ((CraftPlayer)player).getHandle();
        PlayerConnection old = player1.playerConnection;
        PlayerConSniffer connection = new PlayerConSniffer(player1.server, player1.playerConnection.a(), player1);

        for (Field f : old.getClass().getFields()) {
            try {
                f.setAccessible(true);
                Field newF = connection.getClass().getField(f.getName());
                newF.setAccessible(true);
                newF.set(connection, f.get(old));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        player1.playerConnection = connection;
    }

    @EventHandler
    public void playerAction(PlayerActionEvent event) {
        EntityPlayer player = ((CraftPlayer)event.getPlayer()).getHandle();
        if (event.getAction() == PacketPlayInEntityAction.EnumPlayerAction.START_SNEAKING) {
            BigBrother.getSingleton().getLogger().info(player.activeContainer.windowId + "");
            if (event.getPlayer().getOpenInventory().getType() != InventoryType.CRAFTING) {
                BigBrother.getSingleton().getLogger().info(event.getPlayer().getName() + " is using sneak hacks!");
            }
            BigBrother.getSingleton().getLogger().info(event.getPlayer().getName() + " has started sneaking!");
            BigBrother.getSingleton().getServer().getScheduler().runTask(BigBrother.getSingleton(), () -> {
            });
        } else if (event.getAction() == PacketPlayInEntityAction.EnumPlayerAction.STOP_SNEAKING) {
            BigBrother.getSingleton().getLogger().info(event.getPlayer().getName() + " has stopped sneaking!");
            BigBrother.getSingleton().getServer().getScheduler().runTask(BigBrother.getSingleton(), () -> {
            });
        }
    }

    @EventHandler
    public void playerClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            if (((Player)event.getWhoClicked()).isSneaking()) {
                BigBrother.getSingleton().getBanList().add((Player)event.getWhoClicked());
            }
            if (((Player)event.getWhoClicked()).isSprinting()) {
                BigBrother.getSingleton().getBanList().add((Player)event.getWhoClicked());
            }
        }
    }
}
