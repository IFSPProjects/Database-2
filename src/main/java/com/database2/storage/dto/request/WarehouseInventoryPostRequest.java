package com.database2.storage.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record WarehouseInventoryPostRequest(
    @NotNull(message="Quantity is required") Integer quantity,
    @NotNull(message="Product is required") Integer product,
    @NotEmpty(message="State is required") String state,
    Date registerDate
) {}
