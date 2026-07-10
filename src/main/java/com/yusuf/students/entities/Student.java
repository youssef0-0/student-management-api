package com.yusuf.students.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 65)
    private String name;
    @Column(nullable = false)
    private Integer grade;
    @Column(nullable = false)
    private Float gpa;
    @Column(nullable = false, unique = true, length = 20)
    private String phoneNumber;

    public Student() {
    }

    public Student(Integer id, String name, Integer grade, Float gpa, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.gpa = gpa;
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public String getPhoneNumber() {return phoneNumber;}


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber != null ? phoneNumber.trim().replaceAll("\\s+", "") : null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(grade, student.grade) && Objects.equals(gpa, student.gpa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, grade, gpa);
    }
}
