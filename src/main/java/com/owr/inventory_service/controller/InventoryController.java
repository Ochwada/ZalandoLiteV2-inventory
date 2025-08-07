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
    public int getStock(@PathVariable Long productId) {
        return service.getStock(productId);
    }

    /**
     * Retrieves inventory records.
     * *
     * This endpoint supports two modes of operation:
     * - If the {@code warehouse} query parameter is provided and non-blank, it returns all inventory records in that warehouse.
     * - If the {@code warehouse} parameter is omitted or blank, it returns all inventory records in the system.
     * *
     * * - GET /api/inventory
     * * - GET /api/inventory?warehouse=Berlin
     *
     * @param warehouse (optional) the name or ID of the warehouse to filter inventory by
     * @return a list of {@link Inventory} records based on the provided filter (or all if no filter is applied)
     */
    @GetMapping
    public List<Inventory> getInventory(@RequestParam(required = false) String warehouse) {

        if (warehouse != null && !warehouse.isBlank()) {
            return service.getInventories(warehouse);
        } else {
            return service.getAllInventories();
        }
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
    public void createOrUpdate(@RequestBody InventoryRequest request) {
        service.createOrUpdateStock(
                request.productId(),
                request.quantity(),
                request.rack(),
                request.warehouse()
        );
    }


}
