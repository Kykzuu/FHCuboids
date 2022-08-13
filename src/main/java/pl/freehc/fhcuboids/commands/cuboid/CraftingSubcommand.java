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

import java.util.List;

public class CraftingSubcommand implements Listener {

    private static Inventory inv = Bukkit.createInventory(null, 9, "§cKliknij by poznać crafting");;


    public static boolean CraftingSubcommandMain(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        openInventory(p);
        return true;
    }

    public static void initializeItems() {
        inv.addItem(createGuiItem(App.CuboidItem(40, 0).getType(), App.CuboidItem(40, 0).getItemMeta().getDisplayName(), App.CuboidItem(40, 0).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(50, 25000).getType(), App.CuboidItem(50, 25000).getItemMeta().getDisplayName(), App.CuboidItem(50, 25000).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(60, 75000).getType(), App.CuboidItem(60, 75000).getItemMeta().getDisplayName(), App.CuboidItem(60, 75000).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(70, 250000).getType(), App.CuboidItem(70, 250000).getItemMeta().getDisplayName(), App.CuboidItem(70, 250000).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(80, 500000).getType(), App.CuboidItem(80, 500000).getItemMeta().getDisplayName(), App.CuboidItem(80, 500000).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(90, 1000000).getType(), App.CuboidItem(90, 1000000).getItemMeta().getDisplayName(), App.CuboidItem(90, 1000000).getItemMeta().getLore()));
        inv.addItem(createGuiItem(App.CuboidItem(100, 5000000).getType(), App.CuboidItem(100, 5000000).getItemMeta().getDisplayName(), App.CuboidItem(100, 5000000).getItemMeta().getLore()));
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
        Material[] lvl1 = new Material[]{
                Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE,
                Material.DIRT,
                Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE, Material.COBBLESTONE
        };

        Material[] lvl2 = new Material[]{
                Material.COBBLESTONE,Material.COBBLESTONE, Material.COBBLESTONE,
                Material.COBBLESTONE, Material.IRON_BLOCK,Material.COBBLESTONE,
                Material.COBBLESTONE,Material.COBBLESTONE,Material.COBBLESTONE
        };

        Material[] lvl3 = new Material[]{
                Material.IRON_BLOCK,Material.IRON_BLOCK,Material.IRON_BLOCK,
                Material.IRON_BLOCK,Material.DIAMOND_BLOCK,Material.IRON_BLOCK,
                Material.IRON_BLOCK,Material.IRON_BLOCK,Material.IRON_BLOCK
        };

        Material[] lvl4 = new Material[]{
                Material.IRON_BLOCK,Material.IRON_BLOCK,Material.IRON_BLOCK,
                Material.IRON_BLOCK,Material.EMERALD_BLOCK,Material.IRON_BLOCK,
                Material.IRON_BLOCK,Material.IRON_BLOCK,Material.IRON_BLOCK
        };

        Material[] lvl5 = new Material[]{
                Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK,
                Material.DIAMOND_BLOCK, Material.NETHERITE_BLOCK,Material.DIAMOND_BLOCK,
                Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK, Material.DIAMOND_BLOCK
        };

        Material[] lvl6 = new Material[]{
                Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK,
                Material.DIAMOND_BLOCK,Material.BEACON,Material.DIAMOND_BLOCK,
                Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK,Material.DIAMOND_BLOCK        };

        Material[] lvl7 = new Material[]{
                Material.BEACON,Material.BEACON,Material.BEACON,
                Material.BEACON,Material.BEACON,Material.BEACON,
                Material.BEACON,Material.BEACON,Material.BEACON  };
        if(e.getRawSlot() == 0){
            p.openInventory(CraftCuboidInventory.inv(lvl1, 40, 0));
        }

        if(e.getRawSlot() == 1){
            p.openInventory(CraftCuboidInventory.inv(lvl2, 50, 25000));
        }

        if(e.getRawSlot() == 2){
            p.openInventory(CraftCuboidInventory.inv(lvl3, 60, 75000));
        }

        if(e.getRawSlot() == 3){
            p.openInventory(CraftCuboidInventory.inv(lvl4, 70, 250000));
        }

        if(e.getRawSlot() == 4){
            p.openInventory(CraftCuboidInventory.inv(lvl5, 80, 500000));
        }

        if(e.getRawSlot() == 5){
            p.openInventory(CraftCuboidInventory.inv(lvl6, 90, 1000000));
        }

        if(e.getRawSlot() == 6){
            p.openInventory(CraftCuboidInventory.inv(lvl7, 100, 5000000));
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
