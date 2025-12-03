package com.database2.storage.dto.response;

import java.util.List;

import com.database2.storage.entity.State;

public record StateListResponse(String message, List<State> state) {
    
}