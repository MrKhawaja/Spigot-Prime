package xyz.alveehawak.prime.mysql.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import xyz.alveehawak.prime.mysql.basic.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MySQL {
    private static Connection con;

    public MySQL() {
    }

    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database, String port) {
        if (host != null && user != null && password != null && database != null) {
            disconnect(false);

            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=" + Config.getSSL(), user, password);
            } catch (Exception var6) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: " + var6.getMessage());
            }

        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(boolean message) {
        String host = Config.getHost();
        String user = Config.getUser();
        String password = Config.getPassword();
        String database = Config.getDatabase();
        String port = Config.getPort();
        if (isConnected()) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: Already connected");
            }
        } else if (host.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Host is blank");
        } else if (user.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: User is blank");
        } else if (password.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Password is blank");
        } else if (database.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Database is blank");
        } else if (port.equalsIgnoreCase("")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Port is blank");
        } else {
            setConnection(host, user, password, database, port);
        }

    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if (isConnected()) {
                con.close();
                if (message) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SQL disconnected.");
                }
            } else if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        if (con != null) {
            try {
                return !con.isClosed();
            } catch (Exception var1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connection:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var1.getMessage());
            }
        }

        return false;
    }

    public static boolean update(String command) {
        if (command == null) {
            return false;
        } else {
            boolean result = false;
            connect(false);

            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate(command);
                st.close();
                result = true;
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Update:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            disconnect(false);
            return result;
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                Statement st = getConnection().createStatement();
                rs = st.executeQuery(command);
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Query:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            return rs;
        }
    }
}

