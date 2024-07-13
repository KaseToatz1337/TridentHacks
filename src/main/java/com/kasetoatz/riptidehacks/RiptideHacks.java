package com.kasetoatz.riptidehacks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class RiptideHacks implements ClientModInitializer {
    public static MinecraftClient client;
    public static boolean toggled = false;
    public static boolean onGround = true;
    private static KeyBinding keyBinding;
    private static long lastTridentUse = 0;

    @Override
    public void onInitializeClient() {
        client = MinecraftClient.getInstance();
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Riptide", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT, "RiptideHacks"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                toggled = !toggled;
                client.inGameHud.setOverlayMessage(Text.literal("Riptide ").append((toggled) ? Text.literal("ON").styled((style -> style.withColor(Formatting.GREEN))) : Text.literal("OFF").styled((style -> style.withColor(Formatting.RED)))), false);
            }
            if (!onGround && client.player != null && client.player.isOnGround())
            {
                onGround = true;
            }
        });
    }

    public static void setLastTridentUse()
    {
        lastTridentUse = System.currentTimeMillis();
        onGround = false;
    }

    public static boolean shouldAnimate()
    {
        return lastTridentUse > System.currentTimeMillis() - 1000;
    }
}