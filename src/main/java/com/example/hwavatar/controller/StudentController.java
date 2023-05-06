package com.example.hwavatar.controller;

import com.example.hwavatar.model.Student;
import com.example.hwavatar.model.StudentCreationRequest;
import com.example.hwavatar.model.StudentUpdatingRequest;
import com.example.hwavatar.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student add(@RequestBody StudentCreationRequest request) {
        return studentService.add(request.getName(), request.getAge());
    }

    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PutMapping
    public Student update(@RequestBody StudentUpdatingRequest request) {
        return studentService.update(request.getId(), request.getName(), request.getAge());
    }

    @DeleteMapping
    public Student delete(long id) {
        return studentService.delete(id);
    }

    @GetMapping("/count")
    public long getCount() {
        return studentService.getCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }

    @GetMapping("/task-1")
    public List<String> task1() {
        return studentService.getSortStudent();
    }

    @GetMapping("/task-2")
    public Double task2() {
        return studentService.getAllAvgAge();
    }

    @GetMapping("/task-3")
    public String task3() {
        return studentService.getLongestNameFaculty();
    }

    @GetMapping("/task-4")
    public Integer task4() {
        studentService.task4();
        studentService.task4Par();
        return 0;
    }

    @GetMapping("/print-all")
    public void printAll() {
        studentService.printAll();
    }

    @GetMapping("/print-all-sync")
    public void printAllSync() {
        studentService.printAllSync();
    }
}
