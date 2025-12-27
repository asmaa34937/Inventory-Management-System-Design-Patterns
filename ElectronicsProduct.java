/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;


public class ElectronicsProduct extends Product{
    private String brand;
    private int warrantyPeriod;
    public ElectronicsProduct(String sku, String name, double price , int quantity,int threshold){
        super();
        super.setSku(sku);
        super.setName(name);
        super.setPrice(price);
        super.setQuantity(quantity);
        super.setCategory(ProductCategory.ELECTRONICS);
        super.setThreshold(threshold);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
}
