package com.aman.service;

import com.aman.exception.ResourceNotFoundException;
import com.aman.model.Student;
import com.aman.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+id));
    }
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found by id: "+id));
        student.setName(studentDetails.getName());
        student.setSubjects(studentDetails.getSubjects());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.save(student);
    }
}
