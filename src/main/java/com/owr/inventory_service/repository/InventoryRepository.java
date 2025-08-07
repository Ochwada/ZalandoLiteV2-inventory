package com.owr.inventory_service.repository;


import com.owr.inventory_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/*=================================================================================
 * Project: inventory-service
 * File: InventoryRepository
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 12:17 PM
 * Description:  MongoDB repository for inventory records.
 =================================================================================*/
public interface InventoryRepository extends MongoRepository<Inventory, String> {

    /**
     * provide built-in CRUD methods like:
     * - findAll()
     * - findById()
     * - save() // create
     * - deleteById()
     * */
    /**-------------------------------------------------------
     * Custom Queries
     --------------------------------------------------------*/

    /**
     * Finds inventory record by associated product ID.
     *
     * @param productId the ID of the product
     * @return an optional inventory record
     */
    Optional<Inventory> findByProductId(Long productId);

    /**
     * Retrieves all inventory records located in the specified warehouse.
     *
     * @param warehouse the name or ID of the warehouse
     * @return a list of Inventory items stored in that warehouse
     */
    List<Inventory> findByWarehouse(String warehouse);

}
