package io.github.mac_genius.bigbrother.event.player;

import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import net.minecraft.server.v1_8_R3.PacketPlayInKeepAlive;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Mac on 3/19/2016.
 */
public class PlayerPositionUpdateEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private PacketPlayInFlying packet;

    public PlayerPositionUpdateEvent(Player who, PacketPlayInFlying packet) {
        super(who);
        this.packet = packet;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public PacketPlayInFlying getPacket() {
        return packet;
    }
}
