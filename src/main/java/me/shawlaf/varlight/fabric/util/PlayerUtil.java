package me.shawlaf.varlight.fabric.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class PlayerUtil {

    private PlayerUtil() {
        throw new IllegalStateException();
    }

    public static ItemStack getStackInHand(PlayerEntity player, Hand hand) {
        return switch (hand) {
            case MAIN_HAND -> player.getMainHandStack();
            case OFF_HAND -> player.getOffHandStack();
        };
    }

}
