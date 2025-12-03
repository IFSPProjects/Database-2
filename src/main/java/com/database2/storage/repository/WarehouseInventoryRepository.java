package com.database2.storage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.database2.storage.entity.WarehouseInventory;

public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory, Integer>{
    Optional<WarehouseInventory> findDeliveryByid(Integer id);
    Optional<List<WarehouseInventory>> findDeliveriesByproduct_id(Integer product_id);
}
