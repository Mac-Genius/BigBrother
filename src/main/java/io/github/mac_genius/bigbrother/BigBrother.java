package io.github.mac_genius.bigbrother;

import io.github.mac_genius.bigbrother.commands.BanWave;
import io.github.mac_genius.bigbrother.event.player.PlayerListener;
import io.github.mac_genius.bigbrother.player.Info;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mac on 3/17/2016.
 */
public class BigBrother extends JavaPlugin {
    private static BigBrother plugin;
    private ArrayList<Player> banList;
    private HashMap<Player, Info> playerInfo;

    public void onEnable() {
        plugin = this;
        banList = new ArrayList<>();
        playerInfo = new HashMap<>();
        getCommand("banwave").setExecutor(new BanWave());
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getLogger().info("Plugin enabled!");
    }

    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    public static BigBrother getSingleton() {
        return plugin;
    }

    public ArrayList<Player> getBanList() {
        return banList;
    }

    public void resetBanList() {
        banList.clear();
    }
}
