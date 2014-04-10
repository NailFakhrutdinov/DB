package com.kpfu.itis.dao;

import com.kpfu.itis.model.Student;
import com.kpfu.itis.util.HibernateUtils;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImp implements StudentDAO {

    public void addStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Student getStudentById(Long id) throws SQLException {
        Session session = null;
        Student stud = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            stud = (Student) session.load(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return stud;
    }

    public List<Student> getAllStudents() throws SQLException {
        Session session = null;
        List<Student> studs = new ArrayList<Student>();
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            studs = session.createCriteria(Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return studs;
    }

    public void deleteStudent(Student stud) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(stud);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}