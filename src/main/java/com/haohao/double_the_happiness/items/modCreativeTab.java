package com.haohao.double_the_happiness.items;

import net.minecraft.world.item.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.haohao.double_the_happiness.items.moditems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
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
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class modCreativeTab {
    public static final String MOD_TAB_STRING = "creativetab.double_the_happiness_tab";
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "double_the_happiness");
    public static final DeferredHolder<CreativeModeTab,CreativeModeTab> MOD_TAB;
    static {
        MOD_TAB = CREATIVE_MODE_TABS.register(MOD_TAB_STRING,() -> CreativeModeTab.builder().icon(()->new ItemStack(moditems.FIRST_ITEM.get()))
                .title(Component.translatable(MOD_TAB_STRING)).displayItems(((itemDisplayParameters, output) -> {
                    output.accept(moditems.FIRST_ITEM.get());
                }))
                .build());

    }
    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
