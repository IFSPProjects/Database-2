package com.database2.storage.dto.response.views;

import java.util.List;

import com.database2.storage.entity.views.WarehouseView;

public record WarehouseViewResponse(String message, List<WarehouseView> view) {}