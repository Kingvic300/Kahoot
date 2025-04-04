package com.cohort22.mappers;

import com.cohort22.DTOS.request.StudentRequest;
import com.cohort22.DTOS.response.StudentResponse;
import com.cohort22.data.models.Game;
import com.cohort22.data.models.Student;

import java.util.List;

public class StudentMapper extends UserMapper {
    public static Student mapToStudent(StudentRequest studentRequest, Game games) {
        Student student = new Student();
        student.setUsername(studentRequest.getUsername());
        student.setEmail(studentRequest.getEmail());
        student.setPassword(studentRequest.getPassword());
        student.setRole(studentRequest.getRole());
        student.setScore(studentRequest.getTotalScore());
        student.setGameId(games.getId());
        return student;
    }
    public static StudentResponse mapToStudentResponse(String message, Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setMessage(message);
        studentResponse.setUsername(student.getUsername());
        return studentResponse;
    }
}
