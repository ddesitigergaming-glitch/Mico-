package net.invsaver;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class InvSaverClient implements ClientModInitializer {
    public static KeyBinding saveKey;
    public static KeyBinding loadKey;

    @Override
    public void onInitializeClient() {
        // P Key bind - Ditto Inventory Save karne ke liye
        saveKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.invsaver.save",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P, 
                "category.invsaver"
        ));

        // Z Key bind - Ditto Inventory Restore/Load karne ke liye
        loadKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.invsaver.load",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_Z, 
                "category.invsaver"
        ));

        // Har game tick par check karega ki koi key dabayi gayi ya nahi
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            while (saveKey.consumeClick()) {
                InventoryStorage.saveInventory(client.player);
                client.player.sendMessage(Text.literal("§a[AI Mod] Ditto Inventory Saved!"), false);
            }

            while (loadKey.consumeClick()) {
                if (InventoryStorage.loadInventory(client.player)) {
                    client.player.sendMessage(Text.literal("§e[AI Mod] Ditto Inventory Restored!"), false);
                } else {
                    client.player.sendMessage(Text.literal("§c[AI Mod] No saved inventory found! Press P first."), false);
                }
            }
        });
    }
}
