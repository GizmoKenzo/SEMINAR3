package integration;

import model.ItemDTO;
import model.Sale;

/**
 * Handles the logging and processing of ongoing sales including
 * notifying the accounting system and notyfing inventory to update itself.
 */
public class SaleLog {
    private Accounting accounting; 
    private InventoryRegistry inventoryRegistry;

    /**
     * Creates a new instance.
     *
     * @param inventoryRegistry The inventory registry to be notified and updated.
     * @param accounting The accounting system to be notified.
     */
    public SaleLog(InventoryRegistry inventoryRegistry, Accounting accounting) {
        this.inventoryRegistry = inventoryRegistry;
        this.accounting = accounting;
    }

    /**
     * Processes a completed sale by recording the transaction,
     * notifying the accounting system and notyfing inventory to be updated.
     * 
     * @param sale The sale to be processed.
     */
    public void processSale(Sale sale) {
        
        recordTransaction(sale);
        notifyAccounting(sale);
        notifyInventoryRegistry(sale);
    }

    private void recordTransaction(Sale sale) {
        System.out.println("Transaction recorded for sale of total price: " + sale.calculateTotalPrice() + " SEK");
        System.out.println("");
    }

    private void notifyAccounting(Sale sale) {
        accounting.recordSaleTransaction(sale);
    }

    private void notifyInventoryRegistry(Sale sale) {
        for (ItemDTO item : sale.getItems()) {
            inventoryRegistry.updateInventorySystem(item);
        }
    }
}

