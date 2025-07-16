package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.InventoryDto;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.InventoryItem;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    private InventoryDto mapToDto(InventoryItem inventoryItem) {
        InventoryDto dto = new InventoryDto();
        dto.setId(inventoryItem.getId());
        dto.setName(inventoryItem.getName());
        dto.setSku(inventoryItem.getSku());
        dto.setQuantity(inventoryItem.getQuantity());
        dto.setReorderThreshold(inventoryItem.getReorderThreshold());
        return dto;
    }

    private InventoryItem mapToEntity(InventoryDto dto) {
        InventoryItem item = new InventoryItem();
        item.setId(dto.getId());
        item.setName(dto.getName());
        item.setSku(dto.getSku());
        item.setQuantity(dto.getQuantity());
        item.setReorderThreshold(dto.getReorderThreshold());
        item.setLastUpdated(LocalDateTime.now());
        return item;
    }

    @Override
    public InventoryDto createItem(InventoryDto dto) {
        InventoryItem saved = inventoryRepository.save(mapToEntity(dto));
        return mapToDto(saved);
    }

    @Override
    public List<InventoryDto> getAllItems() {
        return inventoryRepository.findAll()
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryDto updateItem(Long id, InventoryDto dto) {
        InventoryItem item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setName(dto.getName());
        item.setSku(dto.getSku());
        item.setQuantity(dto.getQuantity());
        item.setReorderThreshold(dto.getReorderThreshold());
        item.setLastUpdated(LocalDateTime.now());

        return mapToDto(inventoryRepository.save(item));
    }

    @Override
    public void deleteItem(Long id) {
        System.out.println("Attempting to delete item with id: " + id);
        if (!inventoryRepository.existsById(id)){
            throw new RuntimeException("Item not found with id: " + id);
        }
        inventoryRepository.deleteById(id);
        System.out.println("Item deleted with id: " + id);
    }

    @Override
    public List<InventoryDto> getLowStockItems() {
        return inventoryRepository.findByQuantityLessThan(10)
                .stream().map(this::mapToDto)
                .collect(Collectors.toList());
    }



    @Override
    public InventoryItem getUserById(Long userId) {
        return inventoryRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Inventory item not found with ID: " + userId));
    }



}
