package cf.pies.ClientInfo;

import cf.pies.ClientInfo.api.events.LabymodAddonInfoEvent;
import cf.pies.ClientInfo.data.InfoPlayer;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PacketHandler {
    public static void handlePacket(ClientInfo plugin, Player player, Packet<?> object) {
        InfoPlayer info = plugin.getInfoPlayer(player);
        if (object instanceof PacketPlayInCustomPayload) {
            handleCustomPayload(plugin, player, info, (PacketPlayInCustomPayload) object);
        }
    }

    public static void handleCustomPayload(ClientInfo plugin, Player player, InfoPlayer info, PacketPlayInCustomPayload packet) {
        String channel = packet.a();
        PacketDataSerializer serializer = packet.b();

        // Useful debug tool.
//        System.out.println(channel);
//        try {
//            System.out.println(serializer.readBytes(serializer.capacity()).toString(Charset.defaultCharset()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (channel.equals("MC|Brand")) {
            info.clientName = serializer.c(1024);
        }

        if (channel.equals("labymod3:main")) {
            String someUnknownVar = serializer.c(64);
            if (!someUnknownVar.equals("INFO")) return;
            String jsonString = serializer.c(10000);

            Gson gson = new Gson();

            JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
            JsonArray addonsArray = jsonElement.getAsJsonObject().getAsJsonArray("addons");

            for (JsonElement addonElement : addonsArray) {
                JsonObject addonObject = addonElement.getAsJsonObject();
                String addonName = addonObject.get("name").getAsString();
                info.labymodAddons.add(addonName);
            }

            // Packet handler is async
            Bukkit.getScheduler().runTask(plugin, () -> {
                Bukkit.getServer().getPluginManager().callEvent(new LabymodAddonInfoEvent(player, info));
            });
        }

    }
}
