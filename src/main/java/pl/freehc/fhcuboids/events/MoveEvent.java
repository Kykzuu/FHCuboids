package pl.freehc.fhcuboids.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.sql.SQLException;
import java.util.List;

public class MoveEvent implements Listener {
    @EventHandler
    public void OnMove(PlayerMoveEvent e) throws SQLException {
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            Player player = e.getPlayer();
            Location locationTo = e.getTo();
            Location locationFrom = e.getFrom();
            List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();

            for (CuboidModel cuboid : cuboids) {
                if ((locationFrom.getX() < cuboid.getMiX() || locationFrom.getX() > cuboid.getMaX() || locationFrom.getZ() < cuboid.getMiZ() || locationFrom.getZ() > cuboid.getMaZ()) &&
                        locationTo.getX() >= cuboid.getMiX() && locationTo.getX() <= cuboid.getMaX() && locationTo.getZ() >= cuboid.getMiZ() && locationTo.getZ() <= cuboid.getMaZ()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aWkroczyłeś na cuboid gracza §c" + cuboid.getOwnerNickname()));
                    return;
                }
                if ((locationTo.getX() < cuboid.getMiX() || locationTo.getX() > cuboid.getMaX() || locationTo.getZ() < cuboid.getMiZ() || locationTo.getZ() > cuboid.getMaZ()) &&
                        locationFrom.getX() >= cuboid.getMiX() && locationFrom.getX() <= cuboid.getMaX() && locationFrom.getZ() >= cuboid.getMiZ() && locationFrom.getZ() <= cuboid.getMaZ()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aOpuściłeś cuboid gracza §c" + cuboid.getOwnerNickname()));
                    return;
                }
            }
        }
    }
}