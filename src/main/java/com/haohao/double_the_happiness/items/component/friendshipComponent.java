package com.haohao.double_the_happiness.items.component;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.Util;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public record friendshipComponent(UUID creatorId, UUID friendId, String creatorName, String friendName) {
    public static final Codec<friendshipComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    UUIDUtil.CODEC.fieldOf("creator_id").forGetter(friendshipComponent::creatorId),
                    UUIDUtil.CODEC.fieldOf("friend_id").forGetter(friendshipComponent::friendId),
                    Codec.STRING.fieldOf("creator_name").forGetter(friendshipComponent::creatorName),
                    Codec.STRING.fieldOf("friend_name").forGetter(friendshipComponent::friendName)
            ).apply(instance, friendshipComponent::new)
    );
    public static final StreamCodec<ByteBuf, friendshipComponent> STREAM_CODEC = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, friendshipComponent::creatorId,
            UUIDUtil.STREAM_CODEC, friendshipComponent::friendId,
            ByteBufCodecs.STRING_UTF8, friendshipComponent::creatorName,
            ByteBufCodecs.STRING_UTF8, friendshipComponent::friendName,
            friendshipComponent::new
    );

    public static friendshipComponent empty() {
        return new friendshipComponent(Util.NIL_UUID, Util.NIL_UUID, StringUtils.EMPTY, StringUtils.EMPTY);
    }
}
