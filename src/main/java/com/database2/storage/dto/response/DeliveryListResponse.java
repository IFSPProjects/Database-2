package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.Delivery;

public record DeliveryListResponse(String message, List<Delivery> delivery) {
    
}