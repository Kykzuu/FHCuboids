package pl.freehc.fhcuboids.commands.cuboid;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.freehc.fhcuboids.App;

public class CraftCuboidInventory {


    public static Inventory inv(Material[] materials, int size, double price) {
        Inventory in = Bukkit.createInventory(null, 27, "§aCrafting cuboida");
        in.setItem(2,  new ItemStack(materials[0]));
        in.setItem(3,  new ItemStack(materials[1]));
        in.setItem(4,  new ItemStack(materials[2]));
        in.setItem(11, new ItemStack(materials[3]));
        in.setItem(12, new ItemStack(materials[4]));
        in.setItem(13, new ItemStack(materials[5]));
        in.setItem(20, new ItemStack(materials[6]));
        in.setItem(21, new ItemStack(materials[7]));
        in.setItem(22, new ItemStack(materials[8]));
        in.setItem(15, App.CuboidItem(size, price));
        return in;
    }
}