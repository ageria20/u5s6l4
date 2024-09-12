package ageria.u5s6l4.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDTO(
        @NotNull(message = "Name is required")
        @Size(min = 2, max=30, message="Name has to be from 3 to 30 characters")
        String name,
        @NotNull(message = "Surname is required")
        String surname,
        @NotNull(message = "Email  is required")
        String email,
        @NotNull(message = "Birth Date is required")
        LocalDate birthDate) {
}
