package me.shawlaf.varlight.fabric.command.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import me.shawlaf.varlight.fabric.VarLightMod;
import me.shawlaf.varlight.fabric.command.VarLightSubCommand;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public class VarLightCommandStepsize extends VarLightSubCommand {
    public VarLightCommandStepsize(VarLightMod mod) {
        super(mod, "stepsize");
    }

    @Override
    public LiteralArgumentBuilder<ServerCommandSource> build(LiteralArgumentBuilder<ServerCommandSource> root, CommandRegistryAccess registryAccess) {
        root.then(
                RequiredArgumentBuilder.<ServerCommandSource, Integer>argument("stepsize", integer(1, 15))
                        .executes((c) -> execute(c.getSource(), c.getSource().getPlayer(), c.getArgument("stepsize", int.class)))
        );

        return root;
    }

    private int execute(ServerCommandSource source, ServerPlayerEntity player, int stepsize) {
        mod.getPlayerDataManager().getData(player).setStepSize(stepsize);

        source.sendFeedback(() -> Text.literal("Updated Stepsize to " + stepsize), false);

        return stepsize;
    }
}
