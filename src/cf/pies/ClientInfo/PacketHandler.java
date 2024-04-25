package cf.pies.ClientInfo;

import cf.pies.ClientInfo.data.InfoPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import org.bukkit.entity.Player;

public class PacketHandler {
    public static void handlePacket(ClientInfo plugin, Player player, Packet<?> object) {
        InfoPlayer info = plugin.getInfoPlayer(player);
        if (object instanceof PacketPlayInCustomPayload) {
            PacketPlayInCustomPayload packet = (PacketPlayInCustomPayload) object;
            String channel = packet.a();

            PacketDataSerializer serializer = packet.b();
            if (channel.equals("MC|Brand")) {
                info.clientName = serializer.c(1024);
            }

        }
    }
}
