package xyz.alveehawak.prime.mysql.basic;

import xyz.alveehawak.prime.Prime;

public class Config {
    private static final String host = "host";
    private static final String user = "user";
    private static final String password = "password";
    private static final String database = "database";
    private static final String port = "port";
    private static final String ssl = "use_SSL";
    private static final String table = "table";

    public Config() {
    }

    public static void clear() {
        set("host", "localhost", false);
        set("user", "", false);
        set("password", "", false);
        set("database", "", false);
        set("port", "3306", false);
        set("use_SSL", false, false);
        set("table", "prime", false);
    }

    public static void create() {
        set("host", "localhost", true);
        set("user", "", true);
        set("password", "", true);
        set("database", "", true);
        set("port", "3306", true);
        set("use_SSL", false, true);
        set("table", "prime", true);
    }

    public static void reload() {
        Prime.plugin.reloadConfig();
        create();
    }

    public static void setHost(String s) {
        if (!getHost().equalsIgnoreCase(s)) {
            set("host", s, false);
        }

    }

    public static void setUser(String s) {
        if (!getUser().equalsIgnoreCase(s)) {
            set("user", s, false);
        }

    }

    public static void setPassword(String s) {
        if (!getPassword().equalsIgnoreCase(s)) {
            set("password", s, false);
        }

    }

    public static void setDatabase(String s) {
        if (!getDatabase().equalsIgnoreCase(s)) {
            set("database", s, false);
        }

    }

    public static void setPort(String s) {
        if (!getPort().equalsIgnoreCase(s)) {
            set("port", s, false);
        }

    }

    public static void setSSL(boolean b) {
        if (getSSL() != b) {
            set("use_SSL", b, false);
        }

    }

    public static String getHost() {
        return get("host");
    }

    public static String getUser() {
        return get("user");
    }

    public static String getPassword() {
        return get("password");
    }

    public static String getDatabase() {
        return get("database");
    }

    public static String getPort() {
        return get("port");
    }

    public static boolean getSSL() {
        return getBoolean("use_SSL");
    }

    public static String getTable() {
        return get("table");
    }

    private static void set(String name, Object value, boolean checkIfExists) {
        if (name != null && value != null && (!checkIfExists || !Prime.plugin.getConfig().contains(name))) {
            Prime.plugin.getConfig().set(name, value);
            Prime.plugin.saveConfig();
        }
    }

    private static String get(String name) {
        return name != null && Prime.plugin.getConfig().contains(name) ? Prime.plugin.getConfig().getString(name) : "";
    }

    private static boolean getBoolean(String name) {
        return name != null && Prime.plugin.getConfig().contains(name) && Prime.plugin.getConfig().getBoolean(name);
    }
}
