package cf.pies.ClientInfo.listeners;

import cf.pies.ClientInfo.ClientInfo;
import cf.pies.ClientInfo.data.InfoPlayer;
import cf.pies.ClientInfo.PacketHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final ClientInfo plugin;
    public PlayerJoinListener(ClientInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.playerMap.put(player, new InfoPlayer(player));
        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();

        if (pipeline.get("decoder") == null) return;
        if (pipeline.get("ci-packet-listener") != null) return;
        pipeline.addAfter("decoder", "ci-packet-listener", new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channel, Object packet) throws Exception {
                PacketHandler.handlePacket(plugin, player, (Packet<?>) packet);
                super.channelRead(channel, packet);
            }
        });
    }
}
