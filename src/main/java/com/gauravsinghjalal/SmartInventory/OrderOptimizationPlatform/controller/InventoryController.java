package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.controller;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.InventoryDto;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.InventoryItem;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service.InventoryService;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service.OrderProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private OrderProcessingService orderProcessingService;

    @PostMapping
    public InventoryDto createItem(@RequestBody InventoryDto dto) {
        return inventoryService.createItem(dto);


    }

    @GetMapping("/id/{id}")
    public ResponseEntity<InventoryItem> getUserById(@PathVariable("id") Long userId) {
        InventoryItem item = inventoryService.getUserById(userId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @GetMapping
    public List<InventoryDto> getAllItems() {
        return inventoryService.getAllItems();
    }

    @PutMapping("/{id}")
    public InventoryDto updateItem(@PathVariable Long id, @RequestBody InventoryDto dto) {
        return inventoryService.updateItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
    }

    @GetMapping("/low-stock")
    public List<InventoryDto> getLowStockItems() {
        return inventoryService.getLowStockItems();
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<String>> processMultipleOrders(@RequestBody List<Long> orderIds) throws Exception {
        List<CompletableFuture<String>> futures = new ArrayList<>();

        for (Long orderId : orderIds) {
            futures.add(orderProcessingService.processOrder(orderId));
        }

        // Wait until all async tasks complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // Collect results
        List<String> results = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }




}