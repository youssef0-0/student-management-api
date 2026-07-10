package com.yusuf.students.DTOs;

import jakarta.validation.constraints.*;

public record StudentUpdateDTO (
    String name,
    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 4, message = "Grade must not exceed 4")
    Integer grade,
    @DecimalMin(value = "0.0", message = "GPA cannot be negative")
    @DecimalMax(value = "4.0", message = "GPA cannot exceed 4.0")
    Float gpa,
    @Pattern(regexp = "^(\\+20)1[0125]\\d{8}$",
            message = "Phone number must be a valid Egyptian number")
    String phoneNumber
) {}



