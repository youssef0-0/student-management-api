package com.yusuf.students.repositories;

import com.yusuf.students.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<Student> findByPhoneNumber(String phoneNumber);

    List<Student> findByGrade(Integer grade);

    List<Student> findByNameIgnoreCase(String name);

    List<Student> findByGpaGreaterThanEqual(Float gpa);

    List<Student> findByGpaBetween(Float minGpa, Float maxGpa);

    List<Student> findByGpaLessThanEqual(Float gpa);

    List<Student> findByNameIgnoreCaseAndGrade(String name, Integer grade);

    List<Student> findByNameIgnoreCaseOrPhoneNumber(String name, String phoneNumber);
}
