package com.database2.storage.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DeliveryPostRequest(
    @NotNull(message="Quantity is required") Integer quantity,
    @NotNull(message="Product is required") Integer product,
    @NotNull(message="Location is required") Integer location,
    @NotNull(message="Client is required") Integer client,
    Date deliveryDate
) {}
