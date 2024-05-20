package com.aman.service;

import com.aman.exception.ResourceNotFoundException;
import com.aman.model.Student;
import com.aman.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudents();
        assertEquals(2, result.size());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void testSaveStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveStudent(student);
        assertNotNull(result);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setId(1L);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        Student updatedDetails = new Student();
        updatedDetails.setName("Updated Name");

        Student result = studentService.updateStudent(1L, updatedDetails);
        assertEquals("Updated Name", result.getName());
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Aman");
        student.setSubjects(null);
        student.setExams(null);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

//        verify(studentRepository, times(1)).findById(1L);
//        verify(studentRepository, times(1)).delete(student);
    }
}