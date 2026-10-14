package nl.subsentry.subsentry.feature.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record RequestCreateProduct(

        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Price must be a valid decimal number with up to 2 decimal places")
        BigDecimal price
) {
}
