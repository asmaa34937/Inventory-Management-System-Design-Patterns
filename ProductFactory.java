/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

/**
 *
 * @author DELL
 */
public class ProductFactory {
    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold) {
        switch(category) {
            case ELECTRONICS:
                return new ElectronicsProduct(sku, name, price, quantity, threshold);
            case CLOTHING:
                return new ClothingProduct(sku, name, price, quantity, threshold);
            case GROCERY:
                return new GroceryProduct(sku, name, price, quantity, threshold);
            case FURNITURE:
                return new FurnitureProduct(sku, name, price, quantity, threshold);
            case OTHER:
                return new OtherProduct(sku, name, price, quantity, threshold);
            default:
                throw new IllegalArgumentException("Unsupported category: " + category);
        }
    }
    }

