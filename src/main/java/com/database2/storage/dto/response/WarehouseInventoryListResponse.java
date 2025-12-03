package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.WarehouseInventory;

public record WarehouseInventoryListResponse(String message, List<WarehouseInventory> warehouseInventory) {
    
}