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
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidHelper;
import pl.freehc.fhcuboids.CuboidModel;

import java.sql.SQLException;
import java.util.List;

import static pl.freehc.fhcuboids.CuboidHelper.CreateCuboid;
import static pl.freehc.fhcuboids.CuboidHelper.GetPrefixedText;

public class PlaceEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e) throws SQLException {
        createCuboidOnPlace(e, 40, 0); //lvl 1
        createCuboidOnPlace(e, 50, 25000); //lvl 2
        createCuboidOnPlace(e, 60, 75000); //lvl 3
        createCuboidOnPlace(e, 70, 250000); //lvl 4
        createCuboidOnPlace(e, 80, 500000); //lvl 5
        createCuboidOnPlace(e, 90, 1000000); //lvl 6
        createCuboidOnPlace(e, 100, 5000000); //lvl 7
    }

    public void createCuboidOnPlace(BlockPlaceEvent e, int size, double price) throws SQLException {
        if (e.getPlayer().getItemInHand().getType().equals(App.CuboidItem(size, price).getType())) {
            if (!e.getPlayer().getItemInHand().getItemMeta().equals(App.CuboidItem(size, price).getItemMeta()))
                return;
            Location placedBlockLocation = e.getBlockPlaced().getLocation();
            if (!placedBlockLocation.getWorld().getName().equalsIgnoreCase("world")) {
                e.setCancelled(true);
                return;
            }
            int cuboidSize = size;
            List<CuboidModel> cuboids = CuboidHelper.GetAllCuboids();

            for (CuboidModel cuboid : cuboids) {
                if(e.getPlayer().getUniqueId().equals(cuboid.getOwnerUUID())){
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf("§cLimit posiadanych cuboidów zostal przekroczony!"));
                    return;
                }
            }

            Economy economy = App.getEconomy();
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId());
            if(economy.getBalance(offlinePlayer) < price){
                e.getPlayer().sendMessage(GetPrefixedText("Nie masz tylu pieniędzy!"));
                e.setCancelled(true);
                return;
            }

            for (CuboidModel cuboid : cuboids) {
                if (placedBlockLocation.getX() >= cuboid.getMiX() && placedBlockLocation.getX() <= cuboid.getMaX() && placedBlockLocation.getZ() >= cuboid.getMiZ() && placedBlockLocation.getZ() <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf(String.valueOf("§cTen teren jest zajety!")));
                    return;
                }
                if (placedBlockLocation.getX() + cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() + cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() + cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() + cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf("§cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() - cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() - cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() - cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() - cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf("§cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() + cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() + cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() - cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() - cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf("§cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                    return;
                }
                if (placedBlockLocation.getX() - cuboidSize >= cuboid.getMiX() && placedBlockLocation.getX() - cuboidSize <= cuboid.getMaX() && placedBlockLocation.getZ() + cuboidSize >= cuboid.getMiZ() && placedBlockLocation.getZ() + cuboidSize <= cuboid.getMaZ()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(String.valueOf("§cW poblizu jest juz inna dzialka! Poszukaj innego miejsca!"));
                    return;
                }
            }

            CuboidModel cuboid = new CuboidModel();

            cuboid.setMaX(placedBlockLocation.getX() + cuboidSize);
            cuboid.setMiX(placedBlockLocation.getX() - cuboidSize);
            cuboid.setMaZ(placedBlockLocation.getZ() + cuboidSize);
            cuboid.setMiZ(placedBlockLocation.getZ() - cuboidSize);

            cuboid.setjajX(placedBlockLocation.getX());
            cuboid.setjajY(placedBlockLocation.getY());
            cuboid.setjajZ(placedBlockLocation.getZ());

            cuboid.setOwnerNickname(e.getPlayer().getName());
            cuboid.setOwnerUUID(e.getPlayer().getUniqueId());

            cuboid.setSize(cuboidSize);

            cuboid.setCreatedTime(System.currentTimeMillis()/1000);
            cuboid.setExpireTime((System.currentTimeMillis()+(7*86400000))/1000);
            economy.withdrawPlayer(offlinePlayer, price);
            CreateCuboid(cuboid);
            Bukkit.getServer().getWorld("world").getBlockAt(placedBlockLocation).setType(Material.AIR);
            e.getPlayer().sendMessage(String.valueOf("§aZabezpieczyles teren §c" + (cuboidSize * 2) + "§ax§c" + (cuboidSize * 2) + "§a!"));
        }
    }
}
