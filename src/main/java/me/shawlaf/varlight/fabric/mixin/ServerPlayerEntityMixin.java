package me.shawlaf.varlight.fabric.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    // TODO

//    @Inject(
//            at = @At("HEAD"),
//            method = "writeCustomDataToTag"
//    )
//    public void onWriteCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
//        CompoundTag subTag = new CompoundTag();
//
//        VarLightPlayerData data = VarLightMod.INSTANCE.getPlayerDataManager().getData(castThis());
//        data.writeToTag(subTag);
//
//        tag.put(data.getKey().toString(), subTag);
//    }
//
//    @Inject(
//            at = @At("HEAD"),
//            method = "readCustomDataFromTag"
//    )
//    public void onReadCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
//        VarLightPlayerData data = VarLightMod.INSTANCE.getPlayerDataManager().getData(castThis());
//
//        if (tag.contains(data.getKey().toString(), 10)) {
//            data.readFromTag(tag.getCompound(data.getKey().toString()));
//        }
//    }

    private ServerPlayerEntity castThis() {
        return ((ServerPlayerEntity) (Object) this);
    }
}
