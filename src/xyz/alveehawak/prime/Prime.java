package xyz.alveehawak.prime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.alveehawak.prime.mysql.basic.Config;
import xyz.alveehawak.prime.mysql.database.MySQL;
import xyz.alveehawak.prime.mysql.database.SQL;

public class Prime extends JavaPlugin {
    public static Plugin plugin;

    public Prime() {
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Plugin Turned On");
        xyz.alveehawak.prime.mysql.basic.Config.create();
        xyz.alveehawak.prime.mysql.database.MySQL.connect();
        if(xyz.alveehawak.prime.mysql.database.MySQL.isConnected()){
            if(!SQL.tableExists(Config.getTable())){
                MySQL.update("CREATE TABLE `"+Config.getDatabase()+"`.`"+ Config.getTable()+"` ( `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(255) NOT NULL , UNIQUE `ID` (`id`)) ENGINE = InnoDB;");
            }
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Database Initiated");
        }
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Plugin turned off");
        xyz.alveehawak.prime.mysql.database.MySQL.disconnect();
    }
}
