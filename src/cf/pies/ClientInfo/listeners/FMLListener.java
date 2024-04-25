package cf.pies.ClientInfo.listeners;

import cf.pies.ClientInfo.ClientInfo;
import cf.pies.ClientInfo.api.events.ForgeModInfoEvent;
import cf.pies.ClientInfo.data.InfoPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.Arrays;
import java.util.Map;

public class FMLListener implements Listener, PluginMessageListener {
    private final ClientInfo plugin;
    public FMLListener(ClientInfo plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void event(PlayerRegisterChannelEvent event) {
        Player player = event.getPlayer();
        if (event.getChannel().equals("FML|HS")) {
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {-2, 0});
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {0, 2, 0, 0, 0, 0});
            player.sendPluginMessage(plugin, "FML|HS", new byte[] {2, 0, 0, 0, 0});
        }
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        // thank you speedcubing, i still have zero idea what this does though

        if (bytes.length <= 2) return;

        InfoPlayer info = plugin.getInfoPlayer(player);

        // Name, Version
        Map<String, String> mods = info.fmlMods;
        boolean store = false;

        String modName = null, version;

        for (int i = 2; i < bytes.length; store = !store) {
            int end = i + bytes[i] + 1;
            version = new String(Arrays.copyOfRange(bytes, i + 1, end));
            if (store) {
                mods.put(modName, version);
            } else modName = version;
            i = end;
        }

        Bukkit.getServer().getPluginManager().callEvent(new ForgeModInfoEvent(player, info));
    }
}
