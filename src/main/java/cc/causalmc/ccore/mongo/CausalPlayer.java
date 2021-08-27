package cc.causalmc.ccore.mongo;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CausalPlayer {

    private static Map<Player, CausalPlayer> sytaPlayer = new HashMap<>();
    private Player player;
    private PlayerData playerData;

    public CausalPlayer(Player player) {
        this.player = player;
        this.playerData = new PlayerData(player.getUniqueId());
        sytaPlayer.put(player, this);
    }

    public static CausalPlayer getPlayerInfos(Player player){
        return sytaPlayer.get(player);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }


    public int getCoins() {
        return playerData.getCoins();
    }
    public int getCredits() {
        return playerData.getCredits();
    }
    public String getTag() {
        return playerData.getTag();
    }
    public void resetTag() {
        playerData.resetTag();
    }
    public void setTag(String name) {
        playerData.setTag(name);
    }
    public void addCoins(int amount){
        playerData.addCoins(amount);
    }
    public void addCredits(int amount){
        playerData.addCredits(amount);
    }
    public void removeCoins(int amount){
        playerData.removeCoins(amount);
    }
    public void removeCredits(int amount){
        playerData.removeCredits(amount);
    }

    public void playerDisconnect(Player player){
        sytaPlayer.remove(player);
    }

    public static Map<Player, CausalPlayer> getSytaPlayer() {
        return sytaPlayer;
    }
}
