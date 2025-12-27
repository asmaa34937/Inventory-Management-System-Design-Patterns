/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

/**
 *
 * @author DELL
 */
public class ClothingProduct extends Product {
    private String size;
    private String color;
    public ClothingProduct(String sku, String name, double price, int quantity , int threshold){
        super();
        super.setSku(sku);
        super.setName(name);
        super.setPrice(price);
        super.setQuantity(quantity);
        super.setThreshold(threshold);
        super.setCategory(ProductCategory.CLOTHING);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
}
