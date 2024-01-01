package com.kumoe.LeatherHat.block.entity;

import com.kumoe.LeatherHat.LeatherMod;
import com.kumoe.LeatherHat.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LeatherMod.MODID);
    public static final RegistryObject<BlockEntityType<TeleportBlockEntity>> TELEPORT_BE = BLOCK_ENTITIES.register("teleport_block_entity", ()->BlockEntityType.Builder.of(TeleportBlockEntity::new, ModBlocks.TELEPORT_BLOCK.get()).build(null));
}
