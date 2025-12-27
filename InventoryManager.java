package inventory.management.system;

import java.util.ArrayList;
import java.util.List;


public class InventoryManager {
    // -------------------- Singleton Pattern -------------------------------
    private static InventoryManager instance;


    private List<IWarehouse> warehouses;
    private ProductFactory productFactory;


    private InventoryManager() {
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
    }

    public static synchronized InventoryManager getInstance() {
        if (instance == null) {
            instance = new InventoryManager();
        }
        return instance;
    }

    public void addWarehouse(IWarehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void performInventoryCheck() {
        System.out.println("\n--- Performing Global Inventory Check (Proxy Secured) ---");
        for (IWarehouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                if (product.getQuantity() < product.getThreshold()) {
                    System.out.println("Alert: Product " + product.getName() + " is below threshold!");

                }
            }
        }
    }
}