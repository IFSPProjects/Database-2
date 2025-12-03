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

import com.database2.storage.dto.request.DeliveryPostRequest;
import com.database2.storage.dto.response.DeliveryListResponse;
import com.database2.storage.dto.response.DeliveryResponse;
import com.database2.storage.entity.Delivery;
import com.database2.storage.entity.Client;
import com.database2.storage.entity.Product;
import com.database2.storage.entity.Location;
import com.database2.storage.repository.ClientRepository;
import com.database2.storage.repository.DeliveryRepository;
import com.database2.storage.repository.LocationRepository;
import com.database2.storage.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class DeliveryController {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final DeliveryRepository deliveryRepository;

    public DeliveryController(ClientRepository clientRepository, ProductRepository productRepository, LocationRepository locationRepository, DeliveryRepository deliveryRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        this.deliveryRepository = deliveryRepository;
    }
    
    @PostMapping("/addDelivery")
    public ResponseEntity<DeliveryResponse> addDelivery(@Valid @RequestBody DeliveryPostRequest request) {

        Delivery delivery = new Delivery();

        Optional<Client> client = clientRepository.findById(request.client());
        Optional<Product> product = productRepository.findById(request.product());
        Optional<Location>location = locationRepository.findById(request.location());

        delivery.setClient(client.get());
        delivery.setProduct(product.get());
        delivery.setLocation(location.get());
        delivery.setQuantity(request.quantity());
        delivery.setDeliveryDate(request.deliveryDate() == null ? new java.sql.Date(System.currentTimeMillis()) : request.deliveryDate());
        
        deliveryRepository.save(delivery);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DeliveryResponse("Success", delivery));
    }

    @PutMapping("/updateDelivery/{id}")
    public ResponseEntity<DeliveryResponse> addDelivery(@Valid @RequestBody DeliveryPostRequest request, @PathVariable("id") int id) {

        Delivery delivery = new Delivery();

        Optional<Client> client = clientRepository.findById(request.client());
        Optional<Product> product = productRepository.findById(request.product());
        Optional<Location>location = locationRepository.findById(request.location());

        delivery.setId(id);
        delivery.setClient(client.get());
        delivery.setProduct(product.get());
        delivery.setLocation(location.get());
        delivery.setQuantity(request.quantity());
        delivery.setDeliveryDate(request.deliveryDate() == null ? new java.sql.Date(System.currentTimeMillis()) : request.deliveryDate());
        
        deliveryRepository.save(delivery);
        return ResponseEntity.status(HttpStatus.OK).body(new DeliveryResponse("Success", delivery));
    }

    @DeleteMapping("/deleteDelivery/{id}")
    public ResponseEntity<DeliveryResponse> deleteDelivery(@PathVariable("id") int id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (!delivery.isEmpty() && delivery.isPresent()) {
            deliveryRepository.delete(delivery.get());
            return ResponseEntity.status(HttpStatus.OK).body(new DeliveryResponse("Success", delivery.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DeliveryResponse("Nothing was deleted. Verify if the correct ID is inserted.", null));
        }
    }

    @GetMapping("/getDelivery/{id}")
    public ResponseEntity<DeliveryResponse> getDelivery(@PathVariable("id") int id) {
        Delivery delivery = deliveryRepository.findDeliveryByid(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new DeliveryResponse("Success", delivery));
    }

    @GetMapping("/getDeliveries")
    public ResponseEntity<DeliveryListResponse> getDeliveries() {
        List<Delivery> deliverys = deliveryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new DeliveryListResponse("Success", deliverys));
    }
}
