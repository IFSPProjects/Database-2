package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.Location;

public record LocationListResponse(String message, List<Location> Llcation) {
    
}