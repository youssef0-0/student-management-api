package com.yusuf.students.services;

import com.yusuf.students.DTOs.StudentCreateDTO;
import com.yusuf.students.DTOs.StudentMapper;
import com.yusuf.students.DTOs.StudentResponseDTO;
import com.yusuf.students.DTOs.StudentUpdateDTO;
import com.yusuf.students.exceptions.DuplicateResourceException;
import com.yusuf.students.repositories.StudentRepository;
import com.yusuf.students.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all Students & get by id, phone number, grade
    public Page<StudentResponseDTO> getAllStudents(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                    ? Sort.by(Sort.Direction.ASC, sortBy)
                    : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable).map(StudentMapper::toDTO);
    }

    public StudentResponseDTO getStudentById(Integer id) {
        return studentRepository.findById(id).map(StudentMapper::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found"));
    }

    public StudentResponseDTO getStudentByPhoneNumber(String phoneNumber) {
        return studentRepository.findByPhoneNumber(phoneNumber).map(StudentMapper::toDTO).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found"));
    }


    public List<StudentResponseDTO> getStudentByGrade(Integer grade) {
        return studentRepository.findByGrade(grade).stream().map(StudentMapper::toDTO).toList();
    }

    public List<StudentResponseDTO> getStudentByName(String name) {
        return studentRepository.findByNameIgnoreCase(name).stream().map(StudentMapper::toDTO).toList();
    }

    public List<StudentResponseDTO> getStudentsAboveGpa(Float gpa) {

        return studentRepository.findByGpaGreaterThanEqual(gpa).stream().map(StudentMapper::toDTO).toList();
    }


    public List<StudentResponseDTO> getStudentsBetweenGpa(Float minGpa, Float maxGpa) {

        return studentRepository.findByGpaBetween(minGpa, maxGpa).stream().map(StudentMapper::toDTO).toList();
    }

    public List<StudentResponseDTO> getStudentBelowGpa(Float gpa) {
        return studentRepository.findByGpaLessThanEqual(gpa).stream().map(StudentMapper::toDTO).toList();
    }

    public List<StudentResponseDTO> getStudentByNameAndGrade(String name, Integer grade) {
        return studentRepository.findByNameIgnoreCaseAndGrade(name, grade).stream().map(StudentMapper::toDTO).toList();
    }

    public List<StudentResponseDTO> getStudentByNameOrPhoneNumber(String name, String PhoneNumber) {
        return studentRepository.findByNameIgnoreCaseOrPhoneNumber(name, PhoneNumber).stream().map(StudentMapper::toDTO).toList();
    }


    // Add new student
    public void addNewStudent(StudentCreateDTO studentDto) {
        if(studentRepository.existsByPhoneNumber(studentDto.phoneNumber().trim())) {
            throw new DuplicateResourceException("Student already exists");
        }

        Student newStudent = StudentMapper.toEntity(studentDto);
        studentRepository.save(newStudent);
    }

    //Update student
    public void updateStudent(Integer id, StudentUpdateDTO updateStudent) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found"));

        if(updateStudent.name() != null) {
            existingStudent.setName(updateStudent.name());
        }
        if(updateStudent.grade() != null) {
            existingStudent.setGrade(updateStudent.grade());
        }
        if(updateStudent.gpa() != null) {
            existingStudent.setGpa(updateStudent.gpa());
        }
        if(updateStudent.phoneNumber() != null) {
            existingStudent.setPhoneNumber(updateStudent.phoneNumber());
        }

        studentRepository.save(existingStudent);
    }

    //Delete student
    public void deleteStudent(Integer id) {
        if(!studentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found");
        }
        studentRepository.deleteById(id);
    }



}
