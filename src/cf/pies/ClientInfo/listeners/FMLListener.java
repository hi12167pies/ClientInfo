package cf.pies.ClientInfo.listeners;

import cf.pies.ClientInfo.ClientInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FMLListener implements Listener, PluginMessageListener {
    private final ClientInfo plugin;
    public FMLListener(ClientInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(PlayerRegisterChannelEvent event) {
        Player player = event.getPlayer();
        if (event.getChannel().equals("FML|HS")) {
            plugin.getInfoPlayer(player).isModSupported = true;
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {-2, 0});
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {0, 2, 0, 0, 0, 0});
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {2, 0, 0, 0, 0});
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        // thank you speedcubing, i still have zero idea what this does though
        if (bytes.length <= 2) return;
        List<String> mods = new ArrayList<>();
        boolean store = false;
        String name = null, str;
        for (int i = 2; i < bytes.length; store = !store) {
            int end = i + bytes[i] + 1;
            str = new String(Arrays.copyOfRange(bytes, i + 1, end));
            if (store) {
                String modFullName = name + " " + str;
                mods.add(modFullName);
            } else name = str;
            i = end;
        }
        plugin.getInfoPlayer(player).mods = mods;
    }
}
