package net.invsaver;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class InventoryStorage {
    private static final DefaultedList<ItemStack> savedMainInventory = DefaultedList.ofSize(36, ItemStack.EMPTY);
    private static final DefaultedList<ItemStack> savedArmorInventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private static final DefaultedList<ItemStack> savedOffHandInventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private static boolean hasSavedData = false;

    public static void saveInventory(PlayerEntity player) {
        for (int i = 0; i < player.getInventory().main.size(); i++) {
            savedMainInventory.set(i, player.getInventory().main.get(i).copy());
        }
        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            savedArmorInventory.set(i, player.getInventory().armor.get(i).copy());
        }
        savedOffHandInventory.set(0, player.getInventory().offHand.get(0).copy());
        hasSavedData = true;
    }

    public static boolean loadInventory(PlayerEntity player) {
        if (!hasSavedData) return false;

        for (int i = 0; i < player.getInventory().main.size(); i++) {
            player.getInventory().main.set(i, savedMainInventory.get(i).copy());
        }
        for (int i = 0; i < player.getInventory().armor.size(); i++) {
            player.getInventory().armor.set(i, savedArmorInventory.get(i).copy());
        }
        player.getInventory().offHand.set(0, savedOffHandInventory.get(0).copy());

        return true;
    }
}
