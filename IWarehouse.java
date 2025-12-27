
package inventory.management.system;
import java.util.Collection;

public interface IWarehouse {
    void addProduct(Product product, int quantity);
    Product getProductBySku(String sku);
    Collection<Product> getAllProducts();
    void deleteProduct(String sku);
}