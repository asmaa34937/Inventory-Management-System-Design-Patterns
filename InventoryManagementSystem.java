package inventory.management.system;

import javax.swing.SwingUtilities;

public class InventoryManagementSystem {

    public static void main(String[] args) {

        InventoryManager manager = InventoryManager.getInstance();

        IWarehouse w1 = new WarehouseProxy("Central Warehouse");
        manager.addWarehouse(w1);

        // 2 - Factory 
        ProductFactory factory = new ProductFactory();
        Product laptop = factory.createProduct(ProductCategory.ELECTRONICS, "LP-100", "Dell Laptop", 21000, 5, 10);


        w1.addProduct(laptop, 5);

        // 3 - Prototype
        Product clonedLaptop = laptop.clone();
        clonedLaptop.setSku("LP-100-Copy");
        System.out.println("Original SKU: " + laptop.getSku());
        System.out.println("Cloned SKU: " + clonedLaptop.getSku());
        w1.addProduct(clonedLaptop, 3);

        // 4 - Builder
        Product specialProduct = new Product.Builder()
                .setName("Gaming Mouse")
                .setPrice(500)
                .setCategory(ProductCategory.ELECTRONICS)
                .setSku("MS-99")
                .setQuantity(50)
                .setThreshold(10)
                .build();
        w1.addProduct(specialProduct, 50);

        manager.performInventoryCheck();

        SwingUtilities.invokeLater(() -> {
            new InventoryGUI().setVisible(true);
        });
    }
}