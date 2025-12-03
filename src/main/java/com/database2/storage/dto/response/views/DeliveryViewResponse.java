package com.database2.storage.dto.response.views;

import java.util.List;

import com.database2.storage.entity.views.DeliveryView;

public record DeliveryViewResponse(String message, List<DeliveryView> view) {}