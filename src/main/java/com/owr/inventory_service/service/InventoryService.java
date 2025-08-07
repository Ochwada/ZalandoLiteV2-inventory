package com.owr.inventory_service.service;

import com.owr.inventory_service.model.Inventory;
import com.owr.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/*=================================================================================
 * Project: inventory-service
 * File: InventoryService
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 12:38 PM
 * Description: Service layer responsible for managing inventory data.
 * - Provides methods to retrieve and update stock quantities for products.
 =================================================================================*/


@Service
@RequiredArgsConstructor
public class InventoryService {

    private InventoryRepository repository;

    /**
     * Retrieves the current stock quantity for a given product.
     *
     * @param productId the unique ID of the product to check
     * @return the available quantity of the product in stock;
     * returns {@code 0} if the product is not found
     */
    public int getStock(Long productId) {
        Optional<Inventory> inventory = repository.findProductById(productId);

        return inventory
                .map(Inventory::getQuantity)
                .orElse(0);
    }

    /**
     * Retrieves all inventory records for a given warehouse.
     *
     * @param warehouse the name or ID of the warehouse
     * @return a list of inventory records found in that warehouse
     */
    public List<Inventory> getInventories(String warehouse) {
        return repository.findByWarehouse(warehouse);
    }


    /**
     * Creates or updates an inventory record for a specific product.
     * *
     * Updates are only applied to fields if the new value differs from the existing one.
     * If no inventory record exists for the product, a new one is created.
     *
     * @param productId the unique ID of the product
     * @param quantity  the new stock quantity (must be >= 0 to trigger update)
     * @param rack      the updated rack location (only applied if changed)
     * @param warehouse the updated warehouse (only applied if changed)
     */
    public void createOrUpdateStock(
            Long productId, int quantity,
            String rack, String warehouse) {

        Inventory inventory = repository.findProductById(productId)
                .orElse(new Inventory());

        inventory.setProductId(productId);

        // Only update quantity if it's different
        if (quantity >= 0 && inventory.getQuantity() != quantity) {
            inventory.setQuantity(quantity);
        }

        // Only update rack if provided and different
        if (rack != null
                && !rack.isBlank()
                && (inventory.getRack() == null
                || !inventory.getRack().equals(rack))) {
            inventory.setRack(rack);
        }

        // Only update warehouse if provided and different
        if (warehouse != null
                && !warehouse.isBlank()
                && (inventory.getWarehouse() == null
                || !inventory.getWarehouse().equals(warehouse))) {

            inventory.setWarehouse(warehouse);
        }

        repository.save(inventory);
    }

}
