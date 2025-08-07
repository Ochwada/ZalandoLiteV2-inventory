package com.owr.inventory_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/*=================================================================================
 * Project: inventory-service
 * File: Inventory
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 11:25 AM
 * Description: Represents an inventory in a document in MongoDB
 =================================================================================*/

/**
 *  "productId": "123456789",
 *   "quantity": 42,
 *   "rack": "A1-B5",
 *   "warehouse": "Berlin Central",
 *   "lastUpdated": "2025-08-07T15:20:00Z"
 *   */

@Document(collation = "inventories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    /** Unique identifier of the inventory record -in MongoDB (inventories)  */
    @Id
    private  String id;

    /** ID of the product (comes from the product-service)*/
    private  Long productId;

    /** Location of the product in the warehouse*/
    private String rank;

    /** Location of the warehouse*/
    private String warehouse;

    /**
     * When the stock was last updated.
     * *
     * - The timestamp is automatically set by Spring Data whenever the inventory record is created or modified.
     * -The time is formatted as "yyyy-MM-dd HH:mm:ss" and reflects the local timezone of the server environment.
     * * -Example: "2025-08-07 11:50:00"
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime lastUpdated;

    /** Stock in the warehouse*/
    private  int quantity;
}
