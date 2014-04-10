package com.kpfu.itis;

import com.kpfu.itis.dao.StudentDaoImp;
import com.kpfu.itis.model.Student;
import com.kpfu.itis.util.HibernateUtils;
import com.kpfu.itis.util.StudentsUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        StudentDaoImp dao = new StudentDaoImp();

        //Add some students
        StudentsUtils.createStudents();

        //Hibernate functions
        System.out.println("---------------------------All students-----------------------------");
        Criteria crit = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class);
        crit.setMaxResults(50);
        List<Student> studs = crit.list();
        printAll(studs);

        System.out.println("----------------------------BETWEEN 18 and 25 -------------------------");
        studs = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class)
                .add(Restrictions.between("age", 18l, 25l) ).list();
        printAll(studs);

        System.out.println("--------------------------BEGIN WITH A---------------------------------");
        studs = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class)
                .add(Restrictions.ilike("name","a%")).list();
        printAll(studs);

        System.out.println("--------------------------Only 5----------------------------------------");
        studs = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class)
                .setMaxResults(5).list();
        printAll(studs);

        System.out.println("--------------------------Begin with 'I' or age less than 23");
        studs = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class)
                .add(Restrictions.or(Restrictions.lt("age",Long.valueOf(23)),Restrictions.like("name","I%"))).list();
        printAll(studs);

        System.out.println("--------------------------DESC ORDER BY AGE--------------------------");
        studs = HibernateUtils.getSessionFactory().openSession().createCriteria(Student.class).addOrder(Order.desc("age")).list();
        printAll(studs);

        //-----------------------------------------------------------------------------------------------------------------------

        //SQL
        System.out.println("--------------------------ALL STUDENTS---------------------------------");
        studs = HibernateUtils.getSessionFactory().openSession().createSQLQuery("select * from student").addEntity(Student.class).list();
        printAll(studs);

        //Delete all students
        System.out.println("---------------------------DELETE STUDENTS ---------------------------");
        StudentsUtils.deleteStudents();
    }


    private static void printAll(List<Student> studs) {
        for(Student student : studs) {
            System.out.println(student);
        }
    }
}