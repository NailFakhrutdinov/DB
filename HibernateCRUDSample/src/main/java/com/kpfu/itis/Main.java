package com.kpfu.itis;

import com.kpfu.itis.dao.StudentDaoImp;
import com.kpfu.itis.model.Student;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Student n1 = new Student();
        n1.setName("Petr");
        n1.setAge(20l);
        Student n2 = new Student();
        n2.setName("Artur");
        n2.setAge(13l);
        StudentDaoImp dao = new StudentDaoImp();
        dao.addStudent(n1);
        dao.addStudent(n2);
        List<Student> students = dao.getAllStudents();
        for(Student student : students) {
            System.out.println(student);
        }
    }
}
