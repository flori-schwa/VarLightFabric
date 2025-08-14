package me.shawlaf.varlight.fabric.persistence.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public interface INbtSerializable {

    Identifier getKey();

    void writeToTag(NbtCompound tag);

    void readFromTag(NbtCompound tag);

}
