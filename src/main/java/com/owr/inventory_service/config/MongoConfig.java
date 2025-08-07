package com.owr.inventory_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
/*=================================================================================
 * Project: inventory-service
 * File: MongoConfig
 * Created by: Ochwada
 * Created on: 07, 8/7/2025, 11:59 AM
 * Description: Configuration class that enables auditing support for MongoDB
 =================================================================================*/

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    /**
     * By adding the {@code @EnableMongoAuditing} annotation, Spring Data MongoDB will automatically populate fields
     * annotated with {@code @CreatedDate} and {@code @LastModifiedDate} during entity persistence.
     * *
     * This class does not require any methods or fields; its presence in the application context is sufficient for
     * activating auditing behavior.
     */
}
