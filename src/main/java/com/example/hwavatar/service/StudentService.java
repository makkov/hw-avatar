package com.example.hwavatar.service;


import com.example.hwavatar.model.Student;

import java.util.HashMap;
import java.util.Map;

public class StudentService {

    private long counterId = 0;

    private Map<Long, Student> students = new HashMap<>();
}
