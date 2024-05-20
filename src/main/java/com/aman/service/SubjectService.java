package com.aman.service;

import com.aman.exception.ResourceNotFoundException;
import com.aman.model.Subject;
import com.aman.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    private static final String exception_Str = "Subject not found !";

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(exception_Str));
    }

    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject subjectDetails) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(exception_Str));
        subject.setName(subjectDetails.getName());
        subject.setStudents(subjectDetails.getStudents());
        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(exception_Str));
        subjectRepository.delete(subject);
    }
}
