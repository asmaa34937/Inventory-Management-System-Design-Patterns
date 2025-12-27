package inventory.management.system;

// تنفيذ نمط الـ Prototype للسماح باستنساخ الكائنات ونمط الـ Builder لسهولة الإنشاء
public class Product implements Prototype {

    private String sku;
    private String name;
    private double price;
    private int quantity;
    private int threshold;
    private ProductCategory productCategory;

    // Constructor افتراضي
    public Product() {}

    // -------------------- Constructor الخاص بالـ Builder --------------------
    private Product(Builder builder){
        this.sku = builder.sku;
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.threshold = builder.threshold;
        this.productCategory = builder.productCategory;
    }

    // ---------------- Copy Constructor (قلب نمط الـ Prototype) ----------------
    // يُستخدم لإنشاء نسخة جديدة من كائن موجود بالفعل
    public Product(Product original){
        if (original != null) {
            this.sku = original.sku;
            this.name = original.name;
            this.price = original.price;
            this.quantity = original.quantity;
            this.threshold = original.threshold;
            this.productCategory = original.productCategory;
        }
    }

    // ------------------------- Builder Pattern -------------------------
    public static class Builder {
        private String sku;
        private String name;
        private double price;
        private int quantity;
        private int threshold;
        private ProductCategory productCategory;

        public Builder setSku(String sku) { this.sku = sku; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setPrice(double price) { this.price = price; return this; }
        public Builder setQuantity(int quantity) { this.quantity = quantity; return this; }
        public Builder setThreshold(int threshold) { this.threshold = threshold; return this; }
        public Builder setCategory(ProductCategory productCategory) { this.productCategory = productCategory; return this; }

        public Product build() {
            return new Product(this);
        }
    }

    // ----------------------- Prototype: ميثود الـ clone -----------------------
    @Override
    public Product clone() {
        // Copy Constructor
        return new Product(this);
    }

    //-------------------------- Getters & Setters --------------------------
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getThreshold() { return threshold; }
    public void setThreshold(int threshold) { this.threshold = threshold; }

    public ProductCategory getProductCategory() { return productCategory; }
    public void setCategory(ProductCategory productCategory) { this.productCategory = productCategory; }

    @Override
    public String toString() {
        return name + " (SKU: " + sku + ", Quantity: " + quantity + ")";
    }
}