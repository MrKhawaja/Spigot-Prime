package xyz.alveehawak.prime.mysql.basic;

import xyz.alveehawak.prime.mysql.database.MySQL;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private static final String perm;

    public Commands() {
    }

    private void send(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Usage: /mysql <reload/rl>");
        sender.sendMessage(ChatColor.RED + "Usage: /mysql <connect/disconnect/reconnect>");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("mysql")) {
            if (sender instanceof Player && !sender.hasPermission("mysql.admin")) {
                sender.sendMessage(perm);
                return true;
            }

            if (args.length == 0) {
                this.send(sender);
            } else if (args.length == 1) {
                String s = args[0];
                if (!s.equalsIgnoreCase("Reload") && !s.equalsIgnoreCase("Rl")) {
                    if (s.equalsIgnoreCase("Connect")) {
                        sender.sendMessage(ChatColor.GREEN + "MySQL is trying to connect. Check the console for more info.");
                        MySQL.connect();
                    } else if (s.equalsIgnoreCase("Disconnect")) {
                        sender.sendMessage(ChatColor.GREEN + "MySQL is trying to disconnect. Check the console for more info.");
                        MySQL.disconnect();
                    } else if (s.equalsIgnoreCase("Reconnect")) {
                        sender.sendMessage(ChatColor.GREEN + "MySQL is trying to reconnect. Check the console for more info.");
                        MySQL.reconnect();
                    } else {
                        this.send(sender);
                    }
                } else {
                    Config.reload();
                    sender.sendMessage(ChatColor.AQUA + "MySQL configuration reloaded.");
                }
            } else {
                this.send(sender);
            }
        }

        return false;
    }

    static {
        perm = ChatColor.RED + "You are not allowed to do this.";
    }
}
