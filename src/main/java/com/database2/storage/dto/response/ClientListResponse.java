package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.Client;

public record ClientListResponse(String message, List<Client> client) {
    
}