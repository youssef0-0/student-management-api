package com.yusuf.students.DTOs;

import com.yusuf.students.entities.Student;

public class StudentMapper {

    public static Student toEntity(StudentCreateDTO dto) {
        Student student = new Student();
        student.setName(dto.name());
        student.setGrade(dto.grade());
        student.setGpa(dto.gpa());
        student.setPhoneNumber(dto.phoneNumber());
        return student;
    }

    public static StudentResponseDTO toDTO(Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getGrade(),
                student.getGpa()
        );

    }

}
