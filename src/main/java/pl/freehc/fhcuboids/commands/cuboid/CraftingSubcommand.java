package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.freehc.fhcuboids.App;
import pl.freehc.fhcuboids.CuboidConfigurationModel;
import pl.freehc.fhcuboids.PluginConfiguration;
import pl.freehc.fhcuboids.PluginConfigurationModel;

import java.util.List;

public class CraftingSubcommand implements Listener {

    private static final Inventory inv = Bukkit.createInventory(null, 9, "§cKliknij aby poznać crafting");


    public static boolean CraftingSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        openInventory(p);
        return true;
    }

    public static void initializeItems() {
        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.getPluginConfiguration();
        for(CuboidConfigurationModel cuboidConfigurationModel : pluginConfigurationModel.getCuboidsConfig()){
            ItemStack cuboidItem = App.CuboidItem(cuboidConfigurationModel.getSize(), cuboidConfigurationModel.getPrice(), Material.getMaterial(cuboidConfigurationModel.getItem()));
            inv.addItem(createGuiItem(cuboidItem.getType(), cuboidItem.getItemMeta().getDisplayName(), cuboidItem.getItemMeta().getLore()));
        }
    }

    protected static ItemStack createGuiItem(final Material material, final String name, final List<String> lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(lore);

        item.setItemMeta(meta);

        return item;
    }

    public static void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getInventory() != inv) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's

        PluginConfigurationModel pluginConfigurationModel = PluginConfiguration.getPluginConfiguration();
        List<CuboidConfigurationModel> cuboidConfig = pluginConfigurationModel.getCuboidsConfig();

        for (int i = 0; i < cuboidConfig.size(); i++) {
            CuboidConfigurationModel cuboidConfigurationModel = cuboidConfig.get(i);
            List<String> crafting = cuboidConfigurationModel.getCrafting();
            if (e.getRawSlot() == i) {
                Material[] material = new Material[]{
                        Material.getMaterial(crafting.get(0)),Material.getMaterial(crafting.get(1)),Material.getMaterial(crafting.get(2)),
                        Material.getMaterial(crafting.get(3)),Material.getMaterial(crafting.get(4)),Material.getMaterial(crafting.get(5)),
                        Material.getMaterial(crafting.get(6)),Material.getMaterial(crafting.get(7)),Material.getMaterial(crafting.get(8))  };
                p.openInventory(CraftCuboidInventory.inv(material, cuboidConfigurationModel.getSize(), cuboidConfigurationModel.getPrice(), Material.getMaterial(cuboidConfigurationModel.getItem())));
            }
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}
