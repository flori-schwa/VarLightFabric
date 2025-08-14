package me.shawlaf.varlight.fabric.mixin;

import me.shawlaf.varlight.fabric.VarLightMod;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.UpgradeData;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.gen.chunk.BlendingData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.function.BiConsumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Mixin(WorldChunk.class)
public abstract class WorldChunkMixin extends Chunk {

//    @Final
//    @Shadow
//    private ChunkPos pos;

    public WorldChunkMixin(ChunkPos pos, UpgradeData upgradeData, HeightLimitView heightLimitView, Registry<Biome> biomeRegistry, long inhabitedTime, @Nullable ChunkSection[] sectionArray, @Nullable BlendingData blendingData) {
        super(pos, upgradeData, heightLimitView, biomeRegistry, inhabitedTime, sectionArray, blendingData);
    }

    @Shadow
    public abstract BlockState getBlockState(BlockPos pos);

    @Shadow
    public abstract World getWorld();

    // TODO

//    /**
//     * @author Florian
//     */
//    @Overwrite
//    public Stream<BlockPos> getLightSourcesStream() {
//        return StreamSupport.stream(BlockPos.iterate(this.pos.getStartX(), 0, this.pos.getStartZ(), this.pos.getEndX(), 255, this.pos.getEndZ()).spliterator(), false).filter((blockPos) -> {
//            return getLuminance(blockPos) != 0; // TODO Implement custom light source check.
//        });
//    }

    @Override
    public int getLuminance(BlockPos pos) {
        World world = getWorld();
        int vanilla = getBlockState(pos).getLuminance();

        if (world instanceof ServerWorld) {
            int custom = VarLightMod.INSTANCE.getLightStorageManager().getManager((ServerWorld) world).getCustomLuminance(pos, 0);

            return custom != 0 ? custom : vanilla;
        } else {
            return vanilla;
        }
    }
}
