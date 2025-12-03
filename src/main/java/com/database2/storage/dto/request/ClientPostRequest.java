package com.database2.storage.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record ClientPostRequest(
    @NotEmpty(message="Name is required.") String name,
    @NotEmpty(message="CPF is required") String CPF
) {}
