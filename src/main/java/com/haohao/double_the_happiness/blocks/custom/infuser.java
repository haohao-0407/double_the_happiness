package com.haohao.double_the_happiness.blocks.custom;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EnchantingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class infuser extends EnchantingTableBlock {
    public infuser() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).requiresCorrectToolForDrops()
                .strength(2F, 1.5F));
    }

}
