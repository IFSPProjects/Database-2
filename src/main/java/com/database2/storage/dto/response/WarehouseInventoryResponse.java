package com.database2.storage.dto.response;

import com.database2.storage.entity.WarehouseInventory;

public record WarehouseInventoryResponse(String message, WarehouseInventory warehouseInventory) {}
