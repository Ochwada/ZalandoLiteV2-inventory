package com.owr.inventory_service.dto;

import java.time.Instant;
/*=================================================================================
 * Project: inventory-service
 * File: InventoryRequest
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 11:44 AM
 * Description:InventoryRequest is a data transfer object representing an inventory update or creation request with
 * optional rack and warehouse metadata.
 =================================================================================*/

/**
 * Accepts detailed inventory data from clients, including product ID, quantity, rack location, warehouse name, and
 * timestamp for accurate stock management and traceability.
 * *******************************************************
 */

public record InventoryRequest (
    Long productId, int quantity,
    String rack, String warehouse,
    Instant lastUpdated
){

}
