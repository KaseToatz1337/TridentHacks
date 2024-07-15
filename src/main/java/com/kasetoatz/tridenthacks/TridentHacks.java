package com.kasetoatz.tridenthacks;

import com.kasetoatz.tridenthacks.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

public class TridentHacks implements ClientModInitializer {
    public static MinecraftClient client;
    public static boolean onGround = true;
    public static boolean riptideConditions = false;
    public static boolean hasRiptide = false;
    public static int tridentSlot = -1;
    private static KeyBinding keyBinding;
    public static long lastTridentUse = 0;

    @Override
    public void onInitializeClient() {
        Config.load();
        client = MinecraftClient.getInstance();
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Toggle Riptide", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_ALT, "RiptideHacks"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                Config.toggleRiptide = !Config.toggleRiptide;
                Config.save();
                client.inGameHud.setOverlayMessage(Text.of(Config.toggleRiptide ? Config.riptideOnMessage : Config.riptideOffMessage), false);
            }
            if (!onGround && client.player != null && client.player.isOnGround())
            {
                onGround = true;
            }
        });
    }

    public static boolean noNormalRiptide()
    {
        return client.player == null || !riptideConditions || client.player.getActiveItem().getItem() != Items.TRIDENT || !hasRiptide;
    }
}