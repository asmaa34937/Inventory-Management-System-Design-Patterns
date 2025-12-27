package inventory.management.system;

public class FurnitureProduct extends Product {
    private String material;
    private String dimensions;

    public FurnitureProduct(String sku, String name, double price, int quantity, int threshold) {
        super();
        this.setSku(sku);
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setThreshold(threshold);
        this.setCategory(ProductCategory.FURNITURE);
    }

    // Getters and Setters
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }
}