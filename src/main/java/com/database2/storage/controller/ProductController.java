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

import com.database2.storage.dto.request.ProductPostRequest;
import com.database2.storage.dto.response.ProductListResponse;
import com.database2.storage.dto.response.ProductResponse;
import com.database2.storage.entity.Product;
import com.database2.storage.repository.ProductRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/storage")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductPostRequest request) {

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse("Success", product));
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductPostRequest request, @PathVariable("id") int id) {

        Product product = new Product();
        product.setId(id);
        product.setName(request.name());
        product.setDescription(request.description());

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Success", product));
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<ProductResponse> deleteClient(@PathVariable("id") int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isEmpty() && product.isPresent()) {
            productRepository.delete(product.get());
            return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Success", product.get()));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductResponse("Nothing was deleted. Verify if the correct ID is inserted.", null));
        }
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") int id) {
        Product product = productRepository.findProductByid(id).get();
        return ResponseEntity.status(HttpStatus.OK).body(new ProductResponse("Success", product));
    }

    @GetMapping("/getProducts")
    public ResponseEntity<ProductListResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ProductListResponse("Success", products));
    }
    
}
