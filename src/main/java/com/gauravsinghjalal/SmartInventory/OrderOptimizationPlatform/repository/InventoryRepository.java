package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    /**
     * Custom query to find all inventory items where quantity is less than reorder threshold.
     * This supports business logic for identifying low-stock items.
     */
    @Query("SELECT i FROM InventoryItem i WHERE i.quantity < i.reorderThreshold")
    List<InventoryItem> findLowStockItems();

    /**
     * Optional: If you want to manually use a hardcoded threshold (not based on reorderThreshold field),
     * you can keep this as an additional method:
     */
    List<InventoryItem> findByQuantityLessThan(int threshold);
}
