package com.yusuf.students.DTOs;

public record StudentResponseDTO (
    Integer id,
    String name,
    Integer grade,
    Float gpa
) {}

