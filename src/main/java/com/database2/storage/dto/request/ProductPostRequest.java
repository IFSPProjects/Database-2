package com.database2.storage.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ProductPostRequest(
    @NotEmpty(message="Product name is required.") String name,
    String description
) {}
