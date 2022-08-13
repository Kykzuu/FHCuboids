package pl.freehc.fhcuboids.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainGui {
    public void MainGui(Player player){
        ChestGui gui = new ChestGui(4, "§cCuboidy");

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        gui.addPane(background);

        OutlinePane navigationPane = new OutlinePane(3, 1, 3, 1);
        OutlinePane navigationPane2 = new OutlinePane(3, 2, 3, 2);


        ItemStack shop = new ItemStack(Material.ENDER_PEARL);
        ItemMeta shopMeta = shop.getItemMeta();
        shopMeta.setDisplayName("§cTeleport na home");
        shop.setItemMeta(shopMeta);

        navigationPane.addItem(new GuiItem(shop, event -> {
            //navigate to the shop
        }));

        navigationPane2.addItem(new GuiItem(shop, event -> {
            //navigate to the shop
        }));
        navigationPane2.addItem(new GuiItem(shop, event -> {
            //navigate to the shop
        }));
        navigationPane2.addItem(new GuiItem(shop, event -> {
            //navigate to the shop
        }));


        ItemStack beacon = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta beaconMeta = beacon.getItemMeta();
        beaconMeta.setDisplayName("§cDodani gracze");
        beacon.setItemMeta(beaconMeta);

        navigationPane.addItem(new GuiItem(beacon, event -> {
            //navigate to spawn
        }));

        ItemStack bed = new ItemStack(Material.SPLASH_POTION);
        ItemMeta bedMeta = bed.getItemMeta();
        bedMeta.setDisplayName("§cEfekty");
        bed.setItemMeta(bedMeta);

        navigationPane.addItem(new GuiItem(bed, event -> {
            //navigate to home
        }));

        gui.addPane(navigationPane);
        gui.addPane(navigationPane2);
        gui.show(player);
    }
}
