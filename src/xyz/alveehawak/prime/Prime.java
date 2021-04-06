package xyz.alveehawak.prime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Prime extends JavaPlugin {
    public static Plugin plugin;

    public Prime() {
    }

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"EEEEEE Pixeledge er plugin on hoise");
        xyz.alveehawak.prime.mysql.basic.Config.create();
        xyz.alveehawak.prime.mysql.database.MySQL.connect();
        if(xyz.alveehawak.prime.mysql.database.MySQL.isConnected()){
            Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA+"Database Initiated");
        }
        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"Off korlo ke?");
        xyz.alveehawak.prime.mysql.database.MySQL.disconnect();
    }

    private boolean validDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }
}
