/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;


import java.util.Date;
public class GroceryProduct extends Product{
    private Date expiryDate;
    private boolean refrigerated;
    public GroceryProduct(String sku, String name, double price, int quantity, int threshold){
        super();
        super.setSku(sku);
        super.setName(name);
        super.setPrice(price);
        super.setQuantity(quantity);
        super.setCategory(ProductCategory.GROCERY);
        super.setThreshold(threshold);
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRefrigerated() {
        return refrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        this.refrigerated = refrigerated;
    }
    
}
