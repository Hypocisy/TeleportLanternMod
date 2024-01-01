package com.kumoe.LeatherHat.network;

import com.kumoe.LeatherHat.network.messages.TeleportMessage;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class NetworkManager implements Supplier {
    private final ResourceLocation channelName;
    public SimpleChannel channel;
    public String channelVersion = "1.0";

    public NetworkManager(ResourceLocation channelName) {
        this.channelName = channelName;
        this.channel = NetworkRegistry.ChannelBuilder.named(channelName).networkProtocolVersion(() -> channelVersion)
                .clientAcceptedVersions(channelVersion::equals)
                .serverAcceptedVersions(channelVersion::equals).simpleChannel();
        this.channel.registerMessage(id("teleport_block_channel"), TeleportMessage.class, TeleportMessage::encode, TeleportMessage::decode, TeleportMessage::handle);
    }

    @NotNull
    private static int id(String name) {
        return Mth.getInt(name, 9999);
    }

    @Override
    public SimpleChannel get() {
        return this.channel;
    }
}
