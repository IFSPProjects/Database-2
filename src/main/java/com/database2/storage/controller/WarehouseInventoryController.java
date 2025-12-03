package com.database2.storage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database2.storage.dto.request.WarehouseInventoryPostRequest;
import com.database2.storage.dto.response.WarehouseInventoryListResponse;
import com.database2.storage.dto.response.WarehouseInventoryResponse;
import com.database2.storage.entity.Product;
import com.database2.storage.entity.State;
import com.database2.storage.entity.WarehouseInventory;
import com.database2.storage.repository.ProductRepository;
import com.database2.storage.repository.StateRepository;
import com.database2.storage.repository.WarehouseInventoryRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class WarehouseInventoryController {

    private final StateRepository stateRepository;
    private final ProductRepository productRepository;
    private final WarehouseInventoryRepository warehouseInventoryRepository;

    public WarehouseInventoryController(StateRepository stateRepository, ProductRepository productRepository, WarehouseInventoryRepository warehouseInventoryRepository) {
        this.stateRepository = stateRepository;
        this.productRepository = productRepository;
        this.warehouseInventoryRepository = warehouseInventoryRepository;
    }
    
    @PostMapping("/addInventory")
    public ResponseEntity<WarehouseInventoryResponse> addWarehouseInventory(@Valid @RequestBody WarehouseInventoryPostRequest request) {

        WarehouseInventory warehouseInventory = new WarehouseInventory();

        Optional<Product> product = productRepository.findById(request.product());
        Optional<State> state = stateRepository.findStateBystate(request.state());

        warehouseInventory.setProduct(product.get());
        warehouseInventory.setState(state.get());
        warehouseInventory.setQuantity(request.quantity());
        warehouseInventory.setRegisterDate(request.registerDate() == null ? new java.sql.Date(System.currentTimeMillis()) : request.registerDate());

        warehouseInventoryRepository.save(warehouseInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(new WarehouseInventoryResponse("Success", warehouseInventory));
    }

    @PutMapping("/updateInventory/{id}")
    public ResponseEntity<WarehouseInventoryResponse> addWarehouseInventory(@Valid @RequestBody WarehouseInventoryPostRequest request, @PathVariable("id") int id) {

        WarehouseInventory warehouseInventory = new WarehouseInventory();

        Optional<Product> product = productRepository.findById(request.product());
        Optional<State> state = stateRepository.findStateBystate(request.state());

        warehouseInventory.setId(id);
        warehouseInventory.setProduct(product.get());
        warehouseInventory.setState(state.get());
        warehouseInventory.setQuantity(request.quantity());
        warehouseInventory.setRegisterDate(request.registerDate() == null ? new java.sql.Date(System.currentTimeMillis()) : request.registerDate());

        warehouseInventoryRepository.save(warehouseInventory);
        return ResponseEntity.status(HttpStatus.OK).body(new WarehouseInventoryResponse("Success", warehouseInventory));
    }

    @DeleteMapping("/deleteInventory/{id}")
    public ResponseEntity<WarehouseInventoryResponse> deleteWarehouseInventory(@PathVariable("id") int id) {
        Optional<WarehouseInventory> warehouseInventory = warehouseInventoryRepository.findById(id);
        if (!warehouseInventory.isEmpty() && warehouseInventory.isPresent()) {
            warehouseInventoryRepository.delete(warehouseInventory.get());
            return ResponseEntity.status(HttpStatus.OK).body(new WarehouseInventoryResponse("Success", warehouseInventory.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new WarehouseInventoryResponse("Nothing was deleted. Verify if the correct ID is inserted.", null));
        }
    }

    @GetMapping("/getInventory/{id}")
    public ResponseEntity<WarehouseInventoryResponse> getWarehouseInventory(@PathVariable("id") int id) {
        WarehouseInventory warehouseInventory = warehouseInventoryRepository.findById(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new WarehouseInventoryResponse("Success", warehouseInventory));
    }

    @GetMapping("/getWarehouse")
    public ResponseEntity<WarehouseInventoryListResponse> getWarehouseInventories() {
        List<WarehouseInventory> warehouseInventorys = warehouseInventoryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new WarehouseInventoryListResponse("Success", warehouseInventorys));
    }
}
