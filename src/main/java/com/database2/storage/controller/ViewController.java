package com.database2.storage.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.database2.storage.dto.response.views.DeliveryViewResponse;
import com.database2.storage.dto.response.views.WarehouseViewResponse;
import com.database2.storage.entity.views.DeliveryView;
import com.database2.storage.entity.views.WarehouseView;
import com.database2.storage.repository.views.DeliveryViewRepository;
import com.database2.storage.repository.views.WarehouseViewRepository;
    
@RestController
@RequestMapping("/view")
public class ViewController {

    private final DeliveryViewRepository deliveryViewRepository;
    private final WarehouseViewRepository warehouseViewRepository;

    public ViewController(DeliveryViewRepository deliveryViewRepository, WarehouseViewRepository warehouseViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
        this.warehouseViewRepository = warehouseViewRepository;
    }

    @GetMapping
    public String test() {
        return "Testando.";
    }
    
    @GetMapping("/delivery")
    public ResponseEntity<DeliveryViewResponse> useDeliveryView() {
        List<DeliveryView> views = deliveryViewRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new DeliveryViewResponse("Success", views));
    }

    @GetMapping("/warehouse")
    public ResponseEntity<WarehouseViewResponse> useWarehouseView() {
        List<WarehouseView> views = warehouseViewRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new WarehouseViewResponse("Success", views));
    }
}
