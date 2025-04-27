package com.talissonmelo.api.request;

import jakarta.validation.constraints.NotBlank;

public record PostRequest(@NotBlank String title,
                          @NotBlank String body,
                          @NotBlank String author) {
}
