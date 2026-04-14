package com.yusuf.students;

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

    public Page<Student> getAllStudents(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")
                    ? Sort.by(Sort.Direction.ASC, sortBy)
                    : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return studentRepository.findAll(pageable);
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student is not found"));
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student updateStudent) {
        Student existingStudent = getStudentById(id);

        if(updateStudent.getName() != null) {
            existingStudent.setName(updateStudent.getName());
        }
        if(updateStudent.getGrade() != null) {
            existingStudent.setGrade(updateStudent.getGrade());
        }
        if(updateStudent.getGpa() != null) {
            existingStudent.setGpa(updateStudent.getGpa());
        }

        studentRepository.save(existingStudent);
    }

    public void deleteStudent(Integer id) {
        Student existingStudent = getStudentById(id);

        studentRepository.delete(existingStudent);
    }
}
