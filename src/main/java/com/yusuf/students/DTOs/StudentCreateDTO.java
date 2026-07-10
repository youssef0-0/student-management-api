package com.yusuf.students.DTOs;

import jakarta.validation.constraints.*;

public record StudentCreateDTO (
    @NotBlank(message = "Name cannot be empty or whitespace")
    String name,
    @NotNull(message = "Grade is required")
    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 4, message = "Grade must not exceed 4")
    Integer grade,
    @NotNull(message = "Gpa is required")
    @DecimalMin(value = "0.0", message = "GPA cannot be negative")
    @DecimalMax(value = "4.0", message = "GPA cannot exceed 4.0")
    Float gpa,
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+20)1[0125]\\d{8}$",
            message = "Phone number must be a valid Egyptian number")
    String phoneNumber
) {}

