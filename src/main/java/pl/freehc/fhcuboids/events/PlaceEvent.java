package pl.freehc.fhcuboids.events;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.freehc.fhcuboids.*;
import pl.freehc.fhcuboids.configs.CuboidConfigurationModel;
import pl.freehc.fhcuboids.configs.PluginConfiguration;
import pl.freehc.fhcuboids.configs.PluginConfigurationModel;
import pl.freehc.fhcuboids.database.CuboidModel;
import pl.freehc.fhcuboids.services.CuboidService;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import static pl.freehc.fhcuboids.services.CuboidService.*;

public class PlaceEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) throws SQLException {
        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.Companion.getPluginConfiguration();
        for (CuboidConfigurationModel cuboidConfigurationModel : pluginConfigurationModel.getCuboidsConfig()) {
            createCuboidOnPlace(e, cuboidConfigurationModel.getSize(), cuboidConfigurationModel.getPrice(), Material.getMaterial(cuboidConfigurationModel.getItem()));
        }
    }

    public void createCuboidOnPlace(BlockPlaceEvent e, int size, double price, Material item){
        if (e.getPlayer().getItemInHand().getType().equals(App.CuboidItem(size, price, item).getType())) {
            if (!e.getPlayer().getItemInHand().getItemMeta().equals(App.CuboidItem(size, price, item).getItemMeta()))
                return;
            Location placedBlockLocation = e.getBlockPlaced().getLocation();
            if (!placedBlockLocation.getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
                return;
            }
            int cuboidSize = size;
            List<CuboidModel> cuboids = CuboidService.GetAllCuboids();

            for (CuboidModel cuboid : cuboids) {
                if(e.getPlayer().getUniqueId().equals(cuboid.getOwnerUUID())){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cLimit posiadanych cuboidów zostal przekroczony!"));
                    return;
                }
            }

            Economy economy = App.getEconomy();
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
            if(economy.getBalance(offlinePlayer) < price){
                e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cNie masz tylu pieniędzy!"));
                e.setCancelled(true);
                return;
            }

            for (CuboidModel cuboid : cuboids) {
                if (placedBlockLocation.getX() >= cuboid.getMiX() && placedBlockLocation.getX() <= cuboid.getMaX() && placedBlockLocation.getZ() >= cuboid.getMiZ() && placedBlockLocation.getZ() <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cTen teren jest zajęty!"));
                    return;
                }
                if (placedBlockLocation.getX() + cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() + cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() + cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() + cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cW pobliżu jest już działka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() - cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() - cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() - cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() - cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cW pobliżu jest już działka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() + cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() + cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() - cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() - cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cW pobliżu jest już działka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() - cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() - cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() + cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() + cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &cW pobliżu jest już działka! Poszukaj innego miejsca!"));
                    return;
                }
            }

            CuboidModel cuboid = new CuboidModel();

            cuboid.setMaX(placedBlockLocation.getX() + cuboidSize);
            cuboid.setMiX(placedBlockLocation.getX() - cuboidSize);
            cuboid.setMaZ(placedBlockLocation.getZ() + cuboidSize);
            cuboid.setMiZ(placedBlockLocation.getZ() - cuboidSize);

            cuboid.setJajX(placedBlockLocation.getX());
            cuboid.setJajY(placedBlockLocation.getY());
            cuboid.setJajZ(placedBlockLocation.getZ());

            cuboid.setJajPitch(e.getPlayer().getLocation().getPitch());
            cuboid.setJajYaw(e.getPlayer().getLocation().getYaw());

            cuboid.setOwnerNickname(e.getPlayer().getName());
            cuboid.setOwnerUUID(e.getPlayer().getUniqueId());

            cuboid.setSize(cuboidSize);

            cuboid.setCreatedTime(System.currentTimeMillis()/1000);
            cuboid.setExpireTime((System.currentTimeMillis()+(7*86400000))/1000);
            cuboid.setPricePaid(price);
            economy.withdrawPlayer(offlinePlayer, price);

            CreateCuboid(cuboid);
            cuboid.setName("cuboid" + new Random().nextInt(9) + cuboid.getID() + new Random().nextInt(9));
            Bukkit.getServer().getWorld("world").getBlockAt(placedBlockLocation).setType(Material.AIR);
            e.getPlayer().sendMessage(ColoredText("&6&lFree&b&lHC &7Zabezpieczyłeś teren &a" + (cuboidSize * 2) + "x&a" + (cuboidSize * 2) + "&7!"));
        }
    }
}
