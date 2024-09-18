package com.haohao.double_the_happiness.blocks;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
public class modblocks {
    public static final String MODID = "double_the_happiness";
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.Blocks.createBlocks(MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.Items.createItems(MODID);
    public static final DeferredHolder<Block, Block> INFUSER;
    public static final DeferredHolder<Item, BlockItem> INFUSER_ITEM;
    static {
        INFUSER = BLOCKS.register("infuser",() ->new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ENCHANTING_TABLE).requiresCorrectToolForDrops().strength(2F, 1.5F)));
        INFUSER_ITEM = ITEMS.register("infuser",()->new BlockItem(INFUSER.get(),new Item.Properties()));

    }
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
