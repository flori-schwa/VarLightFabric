package me.shawlaf.varlight.fabric.util;

public class OpPermissionLevel {
    public static final int BYPASS_SPAWN_PROTECTION = 1;
    public static final int MANAGE_GAME = 2;
    public static final int MANAGE_PLAYERS = 3;
    public static final int MANAGE_SERVER = 4;
    private OpPermissionLevel() {
        throw new IllegalStateException();
    }

}
