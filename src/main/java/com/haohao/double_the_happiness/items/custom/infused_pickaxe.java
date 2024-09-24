package com.haohao.double_the_happiness.items.custom;

import com.ibm.icu.impl.CacheValue;
import net.minecraft.Util;
import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import com.haohao.double_the_happiness.items.moditems;
import com.haohao.double_the_happiness.items.component.friendshipComponent;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
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
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.UUID;

import com.haohao.double_the_happiness.items.moditems;

import javax.annotation.Nullable;

public class infused_pickaxe extends PickaxeItem {
    public infused_pickaxe(){
        super(moditems.friendness_infused_tier,new Properties().attributes(
                // There are `createAttributes` methods in either the class or subclass of each DiggerItem
                SwordItem.createAttributes(
                        // The tier to use.
                        moditems.friendness_infused_tier,
                        // The type-specific attack damage bonus. 3 for swords, 1.5 for shovels, 1 for pickaxes, varying for axes and hoes.
                        3,
                        // The type-specific attack speed modifier. The player has a default attack speed of 4, so to get to the desired
                        // value of 1.6f, we use -2.4f. -2.4f for swords, -3f for shovels, -2.8f for pickaxes, varying for axes and hoes.
                        -2.4f
                )));
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {


        ItemStack itemInHand = player.getItemInHand(usedHand);
        friendshipComponent contents = itemInHand.getOrDefault(moditems.FRIENDSHIP_CONTENTS.value(), friendshipComponent.empty());
        UUID creatorId = contents.creatorId();
        UUID friendId = contents.friendId();
        UUID userId = player.getUUID();

        String creatorName = contents.creatorName();
        String userName = player.getScoreboardName();

        // 如果主人栏为空，写入主人
        if (Util.NIL_UUID.equals(creatorId)) {
            itemInHand.set(moditems.FRIENDSHIP_CONTENTS.value(), new friendshipComponent(userId, Util.NIL_UUID, userName, StringUtils.EMPTY));
            sendMessage(player, Component.translatable("message.double_the_happiness.creator.success",userName));
            return InteractionResultHolder.success(itemInHand);
        }
        // 如果朋友栏为空
        if (Util.NIL_UUID.equals(friendId)) {
            // 和主人栏 ID 不同，那就是朋友
            if (!creatorId.equals(userId)) {
                itemInHand.set(moditems.FRIENDSHIP_CONTENTS.value(), new friendshipComponent(creatorId, userId, creatorName, userName));
                sendMessage(player, Component.translatable("message.double_the_happiness.friend.success", creatorName));
                return InteractionResultHolder.success(itemInHand);
            } else {
                sendMessage(player, Component.translatable("message.double_the_happiness.creator.same"));
                //return InteractionResultHolder.fail(itemInHand);
            }
        }
        if(friendId.equals(userId)){
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,200,1));
        }




        return InteractionResultHolder.success(itemInHand);
    }


    private void sendMessage(Player player, MutableComponent component) {
        if (!player.level().isClientSide()) {
            player.sendSystemMessage(component);
        }
    }
}
