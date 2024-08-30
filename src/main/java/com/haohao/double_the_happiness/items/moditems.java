package com.haohao.double_the_happiness.items;

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

public class moditems {

    public static final String MODID = "double_the_happiness";
    public static final DeferredRegister.Items ITEMS=
            DeferredRegister.createItems(MODID);
    public static final DeferredItem<Item> FIRST_ITEM = ITEMS.registerSimpleItem("first_item",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_APPLE = ITEMS.registerSimpleItem("halved_apple",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_BAKED_POTATO = ITEMS.registerSimpleItem("halved_baked_potato",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_BEETROOT = ITEMS.registerSimpleItem("halved_beetroot",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_CARROT = ITEMS.registerSimpleItem("halved_carrot",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_CHORUS_FRUIT = ITEMS.registerSimpleItem("halved_chorus_fruit",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_COOKED_CHICKEN = ITEMS.registerSimpleItem("halved_cooked_chicken",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static final DeferredItem<Item> HALVED_COOKED_COD = ITEMS.registerSimpleItem("halved_cooked_cod",new Item.Properties()
            .food(new FoodProperties.Builder().nutrition(2).saturationModifier(2f).build()));
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
