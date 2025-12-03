package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.Product;

public record ProductListResponse(String message, List<Product> product) {
    
}