package com.database2.storage.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LocationPostRequest(
    @NotEmpty(message="Location name is required.") String name,
    String description
) {}
