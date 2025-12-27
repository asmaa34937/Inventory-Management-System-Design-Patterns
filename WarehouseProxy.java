package inventory.management.system;
import java.util.Collection;

public class WarehouseProxy implements IWarehouse {
    private Warehouse realWarehouse; // الكائن الحقيقي
    private String warehouseName;

    public WarehouseProxy(String name) {
        this.warehouseName = name;
    }

    @Override
    public void addProduct(Product product, int quantity) {
        // Lazy Initialization: إنشاء الكائن فقط عند الحاجة [cite: 68, 120]
        if (realWarehouse == null) {
            realWarehouse = new Warehouse(warehouseName);
        }
        realWarehouse.addProduct(product, quantity);
    }

    @Override
    public Collection<Product> getAllProducts() {
        if (realWarehouse == null) {
            realWarehouse = new Warehouse(warehouseName);
        }
        return realWarehouse.getAllProducts();
    }

    @Override
    public Product getProductBySku(String sku) {
        if (realWarehouse == null) {
            realWarehouse = new Warehouse(warehouseName);
        }
        return realWarehouse.getProductBySku(sku);
    }

    @Override
    public void deleteProduct(String sku) {
        if (realWarehouse == null) {
            realWarehouse = new Warehouse(warehouseName);
        }
        realWarehouse.deleteProduct(sku);
    }
}