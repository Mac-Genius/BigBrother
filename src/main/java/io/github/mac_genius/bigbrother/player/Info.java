package io.github.mac_genius.bigbrother.player;

import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/19/2016.
 */
public class Info {
    private long lastKeepAlive;
    private int[] lastPosPacket;
    private Player player;

    public Info(Player player) {
        this.player = player;
        lastKeepAlive = System.currentTimeMillis();
        lastPosPacket = new int[3];
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public long getLastKeepAlive() {
        return lastKeepAlive;
    }

    public void setLastKeepAlive(long lastKeepAlive) {
        this.lastKeepAlive = lastKeepAlive;
    }

    public int[] getLastPosPacket() {
        return lastPosPacket;
    }

    public void setLastPosPacket(int[] lastPosPacket) {
        this.lastPosPacket = lastPosPacket;
    }
}
