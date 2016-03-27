package io.github.mac_genius.bigbrother.event.player;

import net.minecraft.server.v1_8_R3.PacketPlayInEntityAction;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Created by Mac on 3/17/2016.
 */
public class PlayerActionEvent extends PlayerEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled = false;
    private PacketPlayInEntityAction.EnumPlayerAction action;

    public PlayerActionEvent(Player who, PacketPlayInEntityAction.EnumPlayerAction action) {
        super(who);
        this.action = action;
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

    public PacketPlayInEntityAction.EnumPlayerAction getAction() {
        return action;
    }
}
