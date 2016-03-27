package io.github.mac_genius.bigbrother;

import io.github.mac_genius.bigbrother.event.player.PlayerActionEvent;
import io.github.mac_genius.bigbrother.event.player.PlayerKeepAliveEvent;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/17/2016.
 */
public class PlayerConSniffer extends PlayerConnection {
    private static boolean calledEvent = false;
    private static boolean windowClosed = false;
    private static boolean hasMoved = false;
    private static boolean keepAlive = false;
    private static int[] packetSend = new int[3];

    public PlayerConSniffer(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
        /*BigBrother.getSingleton().getServer().getScheduler().runTaskTimerAsynchronously(BigBrother.getSingleton(), () -> {
            BigBrother.getSingleton().getLogger().info("pos+look: " + packetSend[0] + ", pos: " + packetSend[1] + ", look: " + packetSend[2]);
            for (int i = 0; i < packetSend.length; i++) {
                packetSend[i] = 0;
            }
        }, 0, 20);*/
    }

    public void sendPacket(Packet packet) {
        super.sendPacket(packet);
        if (packet instanceof PacketPlayOutEntityStatus) {
            BigBrother.getSingleton().getLogger().info("Entity status");
        } if (packet instanceof PacketPlayOutEntityDestroy) {
            BigBrother.getSingleton().getLogger().info("Entity destroyed");
        } if (packet instanceof PacketPlayOutCombatEvent) {
            BigBrother.getSingleton().getLogger().info("Entity Combat event");
        }
    }

    public void a(PacketPlayInEntityAction packet) {
        /*Player player = Bukkit.getPlayer(getPlayer().getName());
        PlayerActionEvent event = new PlayerActionEvent(player, packet.b());
        if (!calledEvent) {
            BigBrother.getSingleton().getServer().getPluginManager().callEvent(event);
            calledEvent = true;
        }
        if (!event.isCancelled()) {
            super.a(packet);
        }
        calledEvent = false;*/
        super.a(packet);
    }

    public void a(PacketPlayInFlying packet) {
        /*if (!hasMoved) {
            if (packet instanceof PacketPlayInFlying.PacketPlayInPositionLook) {
                BigBrother.getSingleton().getLogger().info("Position and Look:");
                *//*printPacket(packet);*//*
                packetSend[0]++;
                BigBrother.getSingleton().getLogger().info(packet.f() + "");
                BigBrother.getSingleton().getLogger().info("");
            } else if (packet instanceof PacketPlayInFlying.PacketPlayInPosition) {
                BigBrother.getSingleton().getLogger().info("Position:");
                *//*printPacket(packet);*//*
                packetSend[1]++;
                BigBrother.getSingleton().getLogger().info(packet.f() + "");
                BigBrother.getSingleton().getLogger().info("");
            } else if (packet instanceof PacketPlayInFlying.PacketPlayInLook) {
                BigBrother.getSingleton().getLogger().info("Look:");
                *//*printPacket(packet);*//*
                packetSend[2]++;
                BigBrother.getSingleton().getLogger().info(packet.f() + "");
                BigBrother.getSingleton().getLogger().info("");
            }
            hasMoved = true;
        }*/
        super.a(packet);
        hasMoved = false;
    }

    public void printPacket(PacketPlayInFlying packet) {
        BigBrother.getSingleton().getLogger().info("x: " + packet.a() + ", y: " + packet.b() + ", z: " + packet.c());
        BigBrother.getSingleton().getLogger().info("Yaw: " + packet.d() + ", Pitch: " + packet.e());
        BigBrother.getSingleton().getLogger().info("");
    }

    public void a(PacketPlayInKeepAlive packet) {
        /*if (!keepAlive) {
            PlayerKeepAliveEvent event = new PlayerKeepAliveEvent(getPlayer(), packet);
            BigBrother.getSingleton().getServer().getPluginManager().callEvent(event);
        }*/
        super.a(packet);
    }
}
