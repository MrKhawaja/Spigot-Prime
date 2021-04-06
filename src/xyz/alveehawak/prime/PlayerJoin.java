package xyz.alveehawak.prime;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Locale;

import static xyz.alveehawak.prime.mysql.database.MySQL.*;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onBlockBreak(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String name = player.getName().toLowerCase(Locale.ROOT);
        if(!isConnected()){
            reconnect();
        }
        Boolean name_exists = xyz.alveehawak.prime.mysql.database.SQL.exists("name", name, xyz.alveehawak.prime.mysql.basic.Config.getTable());
        if(!name_exists){
            player.kickPlayer("Prime nai KN?");
        }
        if(name_exists){
            player.sendMessage(ChatColor.AQUA+"Welcome to Prime "+player.getName());
        }
    }

}
