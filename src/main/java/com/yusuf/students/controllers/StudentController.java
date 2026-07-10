package com.yusuf.students.controllers;

import com.yusuf.students.DTOs.StudentCreateDTO;
import com.yusuf.students.DTOs.StudentResponseDTO;
import com.yusuf.students.DTOs.StudentUpdateDTO;
import com.yusuf.students.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public Page<StudentResponseDTO> getAllStudents(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size,
                                                   @RequestParam(defaultValue = "id") String sortBy,
                                                   @RequestParam(defaultValue = "asc") String direction) {
        return studentService.getAllStudents(page, size, sortBy, direction);
    }

    @GetMapping("id/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Integer id) {
       return studentService.getStudentById(id);
    }

    @GetMapping("number/{phoneNumber}")
    public StudentResponseDTO getStudentByPhoneNumber(@PathVariable String phoneNumber) {
        return studentService.getStudentByPhoneNumber(phoneNumber);
    }

    @GetMapping("grade/{grade}")
    public List<StudentResponseDTO> getStudentByGrade(@PathVariable Integer grade) {
        return studentService.getStudentByGrade(grade);
    }

    @GetMapping("name/{name}")
    public List<StudentResponseDTO> getStudentByName(@PathVariable String name) {
        return studentService.getStudentByName(name);
    }

    @GetMapping("/gpa-above/{gpa}")
    public List<StudentResponseDTO> getStudentAboveGpa(@PathVariable Float gpa) {
        return studentService.getStudentsAboveGpa(gpa);
    }

    @GetMapping("/gpa-range")
    public List<StudentResponseDTO> getStudentBetweenGpa(@RequestParam(defaultValue = "0") Float minGpa,
                                              @RequestParam(defaultValue = "5") Float maxGpa) {
        return studentService.getStudentsBetweenGpa(minGpa, maxGpa);
    }

    @GetMapping("/gpa-below/{gpa}")
    public List<StudentResponseDTO> getStudentBelowGpa(@PathVariable Float gpa) {
        return studentService.getStudentBelowGpa(gpa);
    }

    @GetMapping("/filter")
    public List<StudentResponseDTO> getStudentByNameAndGrade(@RequestParam String name,
                                                  @RequestParam Integer grade) {
        return studentService.getStudentByNameAndGrade(name, grade);
    }

    @GetMapping("/search")
    public List<StudentResponseDTO> getStudentByNameOrPhoneNumber(@RequestParam(required = false) String name,
                                                       @RequestParam(required = false) String phoneNumber) {
        return studentService.getStudentByNameOrPhoneNumber(name, phoneNumber);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> addNewStudent(@Valid @RequestBody StudentCreateDTO studentDto) {
        studentService.addNewStudent(studentDto);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Student added successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, String>> updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentUpdateDTO student) {
        studentService.updateStudent(id, student);

        Map<String, String> response = new HashMap<>();
        response.put("Message", "Student updated successfully!");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        Map<String, String> response = new HashMap<>();
        response.put("Message", "Student deleted successfully!");
        return ResponseEntity.ok(response);
    }

}
