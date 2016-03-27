package io.github.mac_genius.bigbrother.commands;

import io.github.mac_genius.bigbrother.BigBrother;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Mac on 3/18/2016.
 */
public class BanWave implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("banwave")) {
            for (Player p : BigBrother.getSingleton().getBanList()) {
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), "Using illegal hacks.", null, null);
                p.kickPlayer(ChatColor.RED + "You have been banned for using illegal hacks.");
            }
            BigBrother.getSingleton().resetBanList();
            return true;
        }
        return false;
    }
}
