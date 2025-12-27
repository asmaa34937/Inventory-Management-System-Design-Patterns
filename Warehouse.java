package inventory.management.system;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ده الـ Real Subject (الكائن الحقيقي) بناءً على شرح الـ Proxy.
 * الكائن ده تقيل ومكلف، والبروكسي مش هيعمله create إلا لما نحتاجه (Lazy Initialization).
 */
public class Warehouse implements IWarehouse { // 1. لازم ينفذ الـ Interface
    private String name;
    private Map<String, Product> products;

    public Warehouse(String name) {
        this.name = name;
        this.products = new HashMap<>();

        // محاكاة لعملية ثقيلة عند الإنشاء (زي الاتصال بقاعدة البيانات) كما في ملف الشرح
        System.out.println(">> RealWarehouse: Connecting to heavy database for [" + name + "]...");
    }

    @Override
    public void addProduct(Product product, int quantity) {
        String sku = product.getSku();
        if (products.containsKey(sku)) {
            Product existingProduct = products.get(sku);
            existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
        } else {
            product.setQuantity(quantity);
            products.put(sku, product);
        }
        System.out.println(quantity + " units of " + product.getName() + " added to " + name);
    }

    @Override
    public Product getProductBySku(String sku) {
        return products.get(sku);
    }

    @Override
    public Collection<Product> getAllProducts() {
        return products.values();
    }
    @Override
    public void deleteProduct(String sku) {
        if (products.containsKey(sku)) {
            products.remove(sku);
            System.out.println("Product " + sku + " removed from warehouse.");
        }
    }
}