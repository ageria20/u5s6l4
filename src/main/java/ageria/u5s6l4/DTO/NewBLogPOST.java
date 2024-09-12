package ageria.u5s6l4.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record NewBLogPOST(@NotNull(message = "Category is required")
                           String category,
                           @NotNull(message = "Title is required")
                           String title,
                           @NotNull(message = "Content is required")
                           String content,
                           @NotNull(message = "Reading time is required")
                           int readingTime,
                           @NotNull(message = "ID is required")
                           UUID authorId) {
}
