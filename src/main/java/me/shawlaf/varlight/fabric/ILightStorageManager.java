package me.shawlaf.varlight.fabric;

import me.shawlaf.varlight.fabric.persistence.WorldLightSourceManager;
import me.shawlaf.varlight.fabric.persistence.migrate.LightDatabaseMigratorFabric;
import net.minecraft.server.world.ServerWorld;

import java.io.File;

public interface ILightStorageManager extends IModComponent {

    WorldLightSourceManager getManager(ServerWorld world);

    String getWorldIdentifier(ServerWorld world);

    default File getVarLightSaveDirectory(ServerWorld world) {
        throw new RuntimeException("Not implemented"); // TODO
//        Field saveDirField = ReflectionHelper.getDeclaredField(ThreadedAnvilChunkStorage.class, "saveDir", "field_17707");
//        saveDirField.setAccessible(true);
//
//        File worldRoot;
//
//        try {
//            worldRoot = (File) saveDirField.get(world.getChunkManager().chunkLoadingManager.threadedAnvilChunkStorage);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException("Failed to get Save Directory for World " + ((ServerWorldProperties) world.getLevelProperties()).getLevelName(), e);
//        }
//
//        world.getServer().getSavePath(WorldSavePath.ROOT);
//
//        File saveDir = new File(worldRoot, "varlight");
//
//        if (!saveDir.exists()) {
//            if (!saveDir.mkdirs()) {
//                throw new RuntimeException("Could not create VarLight directory \"" + saveDir.getAbsolutePath() + "\"");
//            }
//        }
//
//        return saveDir;
    }

    LightDatabaseMigratorFabric getDatabaseMigrator();
}
