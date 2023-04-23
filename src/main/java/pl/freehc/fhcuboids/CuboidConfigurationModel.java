package pl.freehc.fhcuboids;

import java.util.List;

public class CuboidConfigurationModel {

    private int size;
    private String item;
    private int price;
    List<String> crafting;

    //getters and setters
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getCrafting() {
        return crafting;
    }

    public void setCrafting(List<String> crafting) {
        this.crafting = crafting;
    }
}
