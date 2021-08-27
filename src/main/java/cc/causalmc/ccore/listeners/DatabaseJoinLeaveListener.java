package cc.causalmc.ccore.listeners;

import cc.causalmc.ccore.CCore;
import cc.causalmc.ccore.mongo.CausalPlayer;
import cc.causalmc.ccore.mongo.DatabaseManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DatabaseJoinLeaveListener implements Listener {



    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        DatabaseManager.loadAccount(e.getPlayer().getUniqueId());

        new CausalPlayer(e.getPlayer());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        CausalPlayer.getSytaPlayer().remove(e.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent e){
        CausalPlayer.getSytaPlayer().remove(e.getPlayer());
    }
}
