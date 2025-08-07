package com.owr.inventory_service.controller;
import com.owr.inventory_service.dto.InventoryRequest;
import com.owr.inventory_service.model.Inventory;
import com.owr.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*=================================================================================
 * Project: inventory-service
 * File: InventoryController
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 2:22 PM
 * Description: REST controller for managing product inventory.
 =================================================================================*/

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    /**
     * Retrieves the current stock quantity for a given product ID.
     *
     * @param productId the ID of the product to look up
     * @return the available stock quantity (0 if not found)
     */
    @GetMapping("/{productId}")
    public int getStock(@PathVariable Long productId){
        return service.getStock(productId);
    }

    /**
     * Retrieves all inventory records for a specific warehouse.
     * *
     * This endpoint accepts a warehouse name or ID as a query parameter and returns a list of all inventory entries
     * stored in that warehouse.
     * - GET /api/inventory?warehouse=Berlin
     *
     * @param warehouse the name or ID of the warehouse to filter inventory by
     * @return a list of {@link Inventory} records located in the specified warehouse
     */
    @GetMapping
    public List<Inventory> getInventory( @RequestParam String warehouse){
        return service.getInventories(warehouse);
    }

    /**
     * Creates or updates an inventory record.
     * *
     * This endpoint accepts an inventory request payload containing product ID, quantity, rack location, and warehouse
     * name. It will update existing inventory if the product already exists, or create a new record if it does not.
     * *
     * - Only changed fields are updated to avoid unnecessary writes.
     * POST /api/inventory
     *
     * @param request the inventory request payload
     */
    @PostMapping
    public void createOrUpdate(@RequestBody InventoryRequest request){
        service.createOrUpdateStock(
                request.productId(),
                request.quantity(),
                request.rack(),
                request.warehouse()
        );
    }


}
