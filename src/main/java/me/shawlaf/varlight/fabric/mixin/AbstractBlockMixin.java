package me.shawlaf.varlight.fabric.mixin;

import me.shawlaf.varlight.fabric.VarLightMod;
import me.shawlaf.varlight.util.pos.IntPosition;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootWorldContext;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {

    @Inject(
            at = @At("RETURN"),
            method = "getDroppedStacks"
    )
    public void getDroppedStacks(BlockState state, LootWorldContext.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        List<ItemStack> drops = cir.getReturnValue();

        if (drops.isEmpty()) {
            return;
        }

        ServerWorld world = builder.getWorld();
        Vec3d pos = builder.get(LootContextParameters.ORIGIN);
        IntPosition intPos = new IntPosition((int) pos.x, (int) pos.y, (int) pos.z); // TODO better

        int customLuminance = getMod().getLightStorageManager().getManager(world).getCustomLuminance(intPos, 0);

        if (customLuminance == 0) {
            return;
        }

        ItemStack tool = builder.get(LootContextParameters.TOOL);

        if (tool.isEmpty()) {
            drops.add(new ItemStack(Items.GLOWSTONE_DUST, 1));
        } else {
            final int silkTouchLevel = EnchantmentHelper.getLevel(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.SILK_TOUCH), tool);
            final int fortuneLevel = EnchantmentHelper.getLevel(world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.FORTUNE), tool);

            if (silkTouchLevel > 0) {
                for (ItemStack drop : drops) { // Should in theory be only one, but /shrug
                    getMod().getGlowingBlockCreator().makeGlowing(drop, customLuminance);
                }
            } else if (fortuneLevel > 0) {
                double chance = 1d - (1.5) * Math.exp(-0.6 * fortuneLevel);
                int count = 1;

                for (int i = 0; i < customLuminance; i++) {
                    if (Math.random() <= chance) {
                        ++count;
                    }
                }

                drops.add(new ItemStack(Items.GLOWSTONE_DUST, count));
            }
        }
    }

    private static VarLightMod getMod() {
        return VarLightMod.INSTANCE;
    }

}
