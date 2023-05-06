package com.example.hwavatar.service;


import com.example.hwavatar.model.Faculty;
import com.example.hwavatar.model.Student;
import com.example.hwavatar.repository.FacultyRepository;
import com.example.hwavatar.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student add(String name, int age) {
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student update(long id, String name, int age) {
        Optional<Student> studentForUpdateOpt = studentRepository.findById(id);

        if (studentForUpdateOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }

        Student studentForUpdate = studentForUpdateOpt.get();
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        studentRepository.save(studentForUpdate);
        return studentForUpdate;
    }

    public Student delete(long id) {
        Optional<Student> studentForDeleteOpt = studentRepository.findById(id);

        if (studentForDeleteOpt.isEmpty()) {
            throw new RuntimeException("Студент c id " + id + " не найден");
        }

        Student studentForDelete = studentForDeleteOpt.get();

        studentRepository.delete(studentForDelete);
        return studentForDelete;
    }

    public long getCount() {
        logger.info("Was invoked method StudentService::getCount");
        return studentRepository.count();
    }

    public double getAverageAge() {
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudent() {
        return studentRepository.getLastFiveStudentsById();
    }

    public List<String> getSortStudent() {
        logger.debug("вызван метод getSortStudent");
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith("А"))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getAllAvgAge() {
        logger.debug("вызван метод getAllAvgAge");
        List<Student> studentList = studentRepository.findAll();
        return studentList.stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }

    public String getLongestNameFaculty() {
        logger.debug("вызван метод getLongestNameFaculty");
        List<Faculty> facultyList = facultyRepository.findAll();
        ;
        return facultyList.stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("No name");
    }

    public Integer task4() {
        long start = System.currentTimeMillis();
        int result = Stream
                .iterate(1, a -> a + 1)
                .limit(100_000_000)
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();

        logger.info("result: {}, time: {}", result, finish - start);
        return result;
    }

    public Integer task4Par() {
        long start = System.currentTimeMillis();
        int result = Stream
                .iterate(1, a -> a + 1)
                .limit(100_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
        long finish = System.currentTimeMillis();

        logger.info("result: {}, time: {}", result, finish - start);
        return result;
    }
}
