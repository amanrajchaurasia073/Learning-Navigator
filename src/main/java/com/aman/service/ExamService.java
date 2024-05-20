package com.aman.service;

import com.aman.exception.ResourceNotFoundException;
import com.aman.model.Exam;
import com.aman.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam not found"));
    }

    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam updateExam(Long id, Exam examDetails) {
        Exam exam = getExamById(id);
        exam.setName(examDetails.getName());
        exam.setSubject(examDetails.getSubject());
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        Exam exam = getExamById(id);
        examRepository.delete(exam);
    }
}
