package com.kpfu.itis.util;


import com.kpfu.itis.dao.StudentDaoImp;
import com.kpfu.itis.model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentsUtils {

    public static void createStudents() throws FileNotFoundException, SQLException {
        StudentDaoImp dao = new StudentDaoImp();
        Scanner scanner = new Scanner(new File("src/main/resources/Students.txt"));
        while(scanner.hasNext()) {
            String man = scanner.nextLine();
            Student student = new Student();
            student.setName(man.substring(0,man.indexOf(",") + 1));
            student.setAge(Long.valueOf(man.substring(man.indexOf(",") + 1,man.length())));
            dao.addStudent(student);
        }
    }
    public static void deleteStudents() throws SQLException {
        StudentDaoImp dao = new StudentDaoImp();
        for(Student student : dao.getAllStudents()) {
            dao.deleteStudent(student);
        }
     }
}
