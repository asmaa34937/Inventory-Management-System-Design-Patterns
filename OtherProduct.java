package inventory.management.system;

public class OtherProduct extends Product {
    private String description;

    public OtherProduct(String sku, String name, double price, int quantity, int threshold) {
        super();
        this.setSku(sku);
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setThreshold(threshold);
        this.setCategory(ProductCategory.OTHER);
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}