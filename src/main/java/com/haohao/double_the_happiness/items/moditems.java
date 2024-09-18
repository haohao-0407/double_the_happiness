package com.haohao.double_the_happiness.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.effects.DamageEntity;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

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

import java.util.function.Supplier;

public class moditems {

    public static final String MODID = "double_the_happiness";
    // This tag will allow us to add these blocks to the incorrect tags that cannot mine them
    public static final TagKey<Block> NEEDS_COPPER_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("double_the_happiness", "needs_copper_tool"));

    // This tag will be passed into our tier
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = TagKey.create(BuiltInRegistries.BLOCK.key(), ResourceLocation.fromNamespaceAndPath("double_the_happiness", "incorrect_for_cooper_tool"));
    public static final Tier friendness_infused_tier =new SimpleTier(
            INCORRECT_FOR_COPPER_TOOL,
    // Determines the durability of the tier.
    // Stone is 131, iron is 250.
        200,
                // Determines the mining speed of the tier. Unused by swords.
                // Stone uses 4, iron uses 6.
                5f,
                // Determines the attack damage bonus. Different tools use this differently. For example, swords do (getAttackDamageBonus() + 4) damage.
                // Stone uses 1, iron uses 2, corresponding to 5 and 6 attack damage for swords, respectively; our sword does 5.5 damage now.
                3f,
                // Determines the enchantability of the tier. This represents how good the enchantments on this tool will be.
                // Gold uses 22, we put copper slightly below that.
                30,
                // Determines the repair ingredient of the tier. Use a supplier for lazy initializing.
                () -> Ingredient.of(Tags.Items.INGOTS_COPPER)
    );
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
    public static final Supplier<SwordItem> FRIENDNESS_INFUSED_SWORD = ITEMS.register("friendness_infused_sword", () -> new SwordItem(
            // The tier to use.
            friendness_infused_tier,
            // The item properties. We don't need to set the durability here because TieredItem handles that for us.
            new Item.Properties().attributes(
                    // There are `createAttributes` methods in either the class or subclass of each DiggerItem
                    SwordItem.createAttributes(
                            // The tier to use.
                            friendness_infused_tier,
                            // The type-specific attack damage bonus. 3 for swords, 1.5 for shovels, 1 for pickaxes, varying for axes and hoes.
                            3,
                            // The type-specific attack speed modifier. The player has a default attack speed of 4, so to get to the desired
                            // value of 1.6f, we use -2.4f. -2.4f for swords, -3f for shovels, -2.8f for pickaxes, varying for axes and hoes.
                            -2.4f
                            )
            )
    ));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
