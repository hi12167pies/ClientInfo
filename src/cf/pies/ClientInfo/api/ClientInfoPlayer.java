package cf.pies.ClientInfo.api;

import java.util.Map;
import java.util.Set;

public interface ClientInfoPlayer {
    /**
     * @return The name (brand) of the client joining
     */
    String getClientName();

    /**
     * @return Returns a map of Mod Names and Version
     */
    Map<String, String> getFMLMods();

    /**
     * @return Returns a set of mod Names
     */
    Set<String> getLabymodAddons();

    /**
     * @return Returns the players ping
     */
    int getPing();

    /**
     * @return Returns the players ip address
     */
    String getIpAddress();
}
