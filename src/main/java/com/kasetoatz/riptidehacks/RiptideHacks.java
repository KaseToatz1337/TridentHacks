package com.kasetoatz.riptidehacks;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;

public class RiptideHacks implements ClientModInitializer {
    public static MinecraftClient client;
    private static long lastTridentUse = 0;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();
    }

    public static void setLastTridentUse()
    {
        lastTridentUse = System.currentTimeMillis();
    }

    public static boolean shouldAnimate()
    {
        return lastTridentUse > System.currentTimeMillis() - 1000;
    }
}
